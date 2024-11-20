package indie.outsource.exceptions;

public class RentNotEndedException extends RuntimeException {
    public RentNotEndedException(String message) {
        super(message);
    }

    public RentNotEndedException() {
    }
}
