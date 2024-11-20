package indie.outsource.exceptions;

public class UserInactiveException extends RuntimeException {
    public UserInactiveException(String message) {
        super(message);
    }

    public UserInactiveException() {
    }
}
