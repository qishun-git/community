package life.cat.community.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String content;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer author;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
