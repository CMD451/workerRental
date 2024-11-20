package indie.outsource.exceptions;

public class WorkerRentedException extends RuntimeException {
    public WorkerRentedException(String message) {
        super(message);
    }

    public WorkerRentedException() {
    }
}
