package life.cat.community.dto;

import life.cat.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String content;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long author;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
