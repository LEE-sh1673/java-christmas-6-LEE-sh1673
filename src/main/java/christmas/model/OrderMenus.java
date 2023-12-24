package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.List;

public class OrderMenus {

    private static final int MAXIMUM_QUANTITY = 20;

    private final List<OrderMenu> menus;

    OrderMenus(final List<OrderMenu> menus) {
        if (isAllBeverage(menus)) {
            throw new OrderException(ErrorType.MENU_ONLY_BEVERAGE);
        }
        if (totalQuantity(menus) >= MAXIMUM_QUANTITY) {
            throw new OrderException(ErrorType.ORDER_QUANTITY_EXCEEDED);
        }
        this.menus = menus;
    }

    private boolean isAllBeverage(final List<OrderMenu> menus) {
        return menus.stream().allMatch(OrderMenu::isBeverage);
    }

    int totalQuantity(final List<OrderMenu> menuItems) {
        return menuItems.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }
}
