package life.cat.community.mapper;

import life.cat.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}