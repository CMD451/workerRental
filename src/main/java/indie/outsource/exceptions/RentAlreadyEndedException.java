package indie.outsource.exceptions;

public class RentAlreadyEndedException extends RuntimeException {
    public RentAlreadyEndedException(String message) {
        super(message);
    }

    public RentAlreadyEndedException() {
    }
}
