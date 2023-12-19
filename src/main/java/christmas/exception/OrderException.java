package christmas.exception;

public class OrderException extends RuntimeException {

    private final ErrorType errorType;

    public OrderException(final ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
