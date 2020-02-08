package life.cat.community.provider;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AWSProvider {

    @Value("${aws.image.bucket-name}")
    private String bucket_name;

    @Value("${aws.image.bucket-region}")
    private String region;

    @Value("${aws.image.bucket-prefix}")
    private String prefix;


    public String uploadImg(InputStream fileStream, String fileName, long size) {
        String key_name = UUID.randomUUID().toString() + "." + fileName.split("\\.")[1];

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).build();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(size);
            s3.putObject(bucket_name, key_name, fileStream, metadata);
            s3.setObjectAcl(bucket_name, key_name, CannedAccessControlList.PublicRead);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        return prefix + key_name;
    }
}
