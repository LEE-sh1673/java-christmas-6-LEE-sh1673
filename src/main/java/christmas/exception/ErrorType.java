package christmas.exception;

public enum ErrorType {

    DATE_OUT_OR_RAGE("주문 일자는 1부터 31까지의 숫자만 가능합니다."),
    MENU_NON_EXISTS("존재하지 않는 메뉴입니다."),
    INVALID_FORMAT_MENU("메뉴 형식이 올바르지 않습니다."),
    MENU_DUPLICATES("메뉴는 중복하여 입력할 수 없습니다."),
    MENU_ONLY_BEVERAGE("음료만 주문할 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    String getMessage() {
        return PREFIX + message;
    }
}
