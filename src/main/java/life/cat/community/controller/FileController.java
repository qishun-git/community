package life.cat.community.controller;

import life.cat.community.provider.AWSProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private AWSProvider awsProvider;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("imageURL");
        String imageURL = null;
        try {
            long size = file.getSize();
            imageURL = awsProvider.uploadImg(file.getInputStream(), file.getOriginalFilename(), size);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        FileDTO fileDTO = new FileDTO();
//        fileDTO.setFile_path(imageURL);
        return imageURL;
    }
}
