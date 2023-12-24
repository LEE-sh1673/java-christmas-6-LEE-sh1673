package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;

public class OrderMenu {

    private static final int MIN_QUANTITY = 1;

    private final Menu menu;
    private final int quantity;

    OrderMenu(final String name, final int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new OrderException(ErrorType.INVALID_ORDER_QUANTITY);
        }
        this.menu = Menu.findByName(name);
        this.quantity = quantity;
    }

    boolean isBeverage() {
        return menu.isBeverage();
    }

    int getQuantity() {
        return quantity;
    }
}
