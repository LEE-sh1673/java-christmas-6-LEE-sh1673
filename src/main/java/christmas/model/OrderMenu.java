package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;
import static christmas.exception.ErrorType.MAX_ORDER_EXCEEDED;

import christmas.MenuType;
import java.util.Objects;
import java.util.regex.Pattern;

public class OrderMenu {

    private static final Pattern MENU_PATTERN = Pattern.compile("\\S+-[0-9]+");

    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_QUANTITY = 20;

    private final Menu menu;

    private final int quantity;

    public OrderMenu(final String nameWithQuantity) {
        validateFormat(nameWithQuantity);
        final String[] menuInfo = nameWithQuantity.split("-");
        this.menu = Menu.findMenu(menuInfo[0]);
        this.quantity = Integer.parseInt(menuInfo[1]);
        validate(quantity);
    }

    private void validateFormat(final String name) {
        if (name == null || !MENU_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
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

    public boolean isBeverage() {
        return menu.getCategory() == MenuType.BEVERAGE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderMenu other = (OrderMenu) o;
        return menu == other.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
