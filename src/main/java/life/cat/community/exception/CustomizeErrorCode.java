package life.cat.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    POST_NOT_FOUND(2001, "Post not found. Meow~"),
    TARGET_PARAM_NOT_FOUND(2002, "Nothing to reply. Whoo!"),
    NO_LOGIN(2003, "Please log in first."),
    SYS_ERROR(2004, "System Error."),
    TYPE_PARAM_WRONG(2005, "Comment type not exist."),
    COMMENT_NOT_FOUND(2006, "Comment not found."),
    COMMENT_IS_EMPTY(2006, "Please enter some comment."),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

}
