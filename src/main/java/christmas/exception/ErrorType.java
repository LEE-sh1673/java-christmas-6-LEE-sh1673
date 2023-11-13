package christmas.exception;

public enum ErrorType {

    INVALID_NUMBER_FORMAT("숫자 형식이 올바르지 않습니다."),
    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 않은 주문입니다."),
    MAX_ORDER_EXCEEDED("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    ONLY_BEVERAGE_ORDERED("음료만 주문 시, 주문할 수 없습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_POSTFIX = " 다시 입력해 주세요.";

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message + ERROR_POSTFIX;
    }
}
