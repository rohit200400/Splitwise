package practice.project.splitwise.exception;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException() {
    }

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }

    public GroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
