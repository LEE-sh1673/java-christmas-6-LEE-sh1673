package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;

import java.util.Objects;
import java.util.regex.Pattern;

public class OrderMenu {

    private static final Pattern MENU_PATTERN = Pattern.compile("\\S+-[0-9]+");

    private static final int MIN_ORDER_QUANTITY = 1;

    private final Menu menu;

    private final int quantity;

    public OrderMenu(final String nameWithQuantity) {
        validateFormat(nameWithQuantity);
        final String[] menuInfo = nameWithQuantity.split("-");
        this.menu = Menu.findMenu(menuInfo[0]);
        this.quantity = Integer.parseInt(menuInfo[1]);
        validateQuantity(quantity);
    }

    private void validateFormat(final String name) {
        if (name == null || !MENU_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateQuantity(final long quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
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

    public boolean matchType(final MenuType menuType) {
        return menu.matchType(menuType);
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
