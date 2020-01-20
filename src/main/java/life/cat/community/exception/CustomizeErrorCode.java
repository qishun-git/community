package life.cat.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    POST_NOT_FOUND("Post not found. Meow~");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
