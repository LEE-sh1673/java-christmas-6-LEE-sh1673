package christmas.exception;

public enum ErrorType {

    INVALID_ORDER("주문 일자는 1부터 31까지의 숫자만 가능합니다."),
    MENU_NON_EXISTS("존재하지 않는 메뉴입니다.");

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    String getMessage() {
        return PREFIX + message;
    }
}
