package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.regex.Pattern;

class OrderMenu {

    private static final Pattern MENU_PATTERN = Pattern.compile("\\S+-[1-9]+");
    private static final String MENU_SPLITTER = "-";
    private static final int MENU_NAME = 0;
    private static final int MENU_QUANTITY = 1;

    private final Menu menu;

    private final int quantity;

    private OrderMenu(final String menu) {
        if (!MENU_PATTERN.matcher(menu).matches()) {
            throw new OrderException(ErrorType.INVALID_FORMAT_MENU);
        }
        final String[] splitMenu = menu.split(MENU_SPLITTER);
        this.menu = Menu.findByName(splitMenu[MENU_NAME]);
        this.quantity = Integer.parseInt(splitMenu[MENU_QUANTITY]);
    }

    static OrderMenu select(final String menu) {
        return new OrderMenu(menu);
    }

    boolean isBeverage() {
        return menu.isBeverage();
    }
}
