package life.cat.community.cache;

import life.cat.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO species = new TagDTO();
        species.setCategoryName("species");
        species.setTags(Arrays.asList("cat", "Alien"));
        tagDTOS.add(species);
        TagDTO breed = new TagDTO();
        breed.setCategoryName("breed");
        breed.setTags(Arrays.asList("bengal"));
        tagDTOS.add(breed);
        TagDTO age = new TagDTO();
        age.setCategoryName("age");
        age.setTags(Arrays.asList("0-6month", "6-12month", "1-2year"));
        tagDTOS.add(age);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        tags = tags.replaceAll("\\s", "");
        String[] split = tags.split(",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
