package christmas.exception;

public enum ErrorType {

    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
