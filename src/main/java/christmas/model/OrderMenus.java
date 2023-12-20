package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.List;

class OrderMenus {

    private final List<OrderMenu> menus;

    OrderMenus(final List<OrderMenu> menus) {
        validate(menus);
        this.menus = menus;
    }

    private void validate(final List<OrderMenu> menus) {
        if (isAllBeverage(menus)) {
            throw new OrderException(ErrorType.MENU_ONLY_BEVERAGE);
        }
    }

    private boolean isAllBeverage(final List<OrderMenu> menus) {
        return menus.stream().allMatch(OrderMenu::isBeverage);
    }
}
