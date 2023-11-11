package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;
import static christmas.exception.ErrorType.MAX_ORDER_EXCEEDED;

public class OrderMenu {

    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_QUANTITY = 20;

    private final Menu menu;

    private final int quantity;

    public OrderMenu(final String menuName, final int quantity) {
        validate(quantity);
        this.menu = Menu.findMenu(menuName);
        this.quantity = quantity;
    }

    private void validate(final long quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
        if (quantity > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(MAX_ORDER_EXCEEDED.getMessage());
        }
    }

    public String getName() {
        return menu.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPrize() {
        return menu.getPrize() * quantity;
    }
}
