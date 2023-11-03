package practice.project.splitwise.exception;

public class UserNotMemberOfGroupException extends Exception {
    public UserNotMemberOfGroupException() {
    }

    public UserNotMemberOfGroupException(String message) {
        super(message);
    }

    public UserNotMemberOfGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotMemberOfGroupException(Throwable cause) {
        super(cause);
    }

    public UserNotMemberOfGroupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
