package life.cat.community.mapper;

import life.cat.community.dto.PostQueryDTO;
import life.cat.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(PostQueryDTO postQueryDTO);

    List<Question> selectBySearch(PostQueryDTO postQueryDTO);
}