package life.cat.community.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
