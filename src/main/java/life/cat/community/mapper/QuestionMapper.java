package life.cat.community.mapper;

import life.cat.community.dto.QuestionDTO;
import life.cat.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, content, gmt_create, gmt_modified, author, tag) values (#{title}, #{content}, #{gmtCreate}, #{gmtModified}, #{author}, #{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where author = #{userId} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,
                                @Param(value = "offset") Integer offset,
                                @Param(value = "size") Integer size);

    @Select("select count(1) from question where author = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);
}
