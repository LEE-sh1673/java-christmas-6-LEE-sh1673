package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class OrderMenus {

    private static final String MENU_REGEX = ",";
    private static final int MAXIMUM_QUANTITY = 20;

    private final List<OrderMenu> menus;

    OrderMenus(final String menus) {
        this.menus = makeMenus(menus);
    }

    private List<OrderMenu> makeMenus(final String menus) {
        final List<OrderMenu> orderMenus = selectMenus(menus);
        validate(orderMenus);
        return orderMenus;
    }

    private List<OrderMenu> selectMenus(final String menus) {
        return Arrays.stream(menus.split(MENU_REGEX))
                .map(OrderMenu::select)
                .toList();
    }

    private void validate(final List<OrderMenu> orderMenus) {
        if (isMenuDuplicated(orderMenus)) {
            throw new OrderException(ErrorType.MENU_DUPLICATES);
        }
        if (isAllBeverage(orderMenus)) {
            throw new OrderException(ErrorType.MENU_ONLY_BEVERAGE);
        }
        if (isOutOfQuantity(orderMenus)) {
            throw new OrderException(ErrorType.ORDER_QUANTITY_EXCEEDED);
        }
    }

    private boolean isMenuDuplicated(final List<OrderMenu> orderMenus) {
        return orderMenus.size() != new HashSet<>(orderMenus).size();
    }

    private boolean isAllBeverage(final List<OrderMenu> menus) {
        return menus.stream().allMatch(OrderMenu::isBeverage);
    }

    private boolean isOutOfQuantity(final List<OrderMenu> menuItems) {
        final int totalQuantity = menuItems.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum();
        return totalQuantity >= MAXIMUM_QUANTITY;
    }
}
