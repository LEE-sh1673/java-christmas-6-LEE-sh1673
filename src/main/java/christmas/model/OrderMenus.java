package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.HashSet;
import java.util.List;

public class OrderMenus {

    private static final int MAXIMUM_QUANTITY = 20;

    private final List<OrderMenu> menus;

    OrderMenus(final List<OrderMenu> menus) {
        if (isMenuDuplicated(menus)) {
            throw new OrderException(ErrorType.MENU_DUPLICATES);
        }
        if (isAllBeverage(menus)) {
            throw new OrderException(ErrorType.MENU_ONLY_BEVERAGE);
        }
        if (totalQuantity(menus) >= MAXIMUM_QUANTITY) {
            throw new OrderException(ErrorType.ORDER_QUANTITY_EXCEEDED);
        }
        this.menus = menus;
    }

    private static boolean isMenuDuplicated(final List<OrderMenu> orderMenus) {
        return orderMenus.size() != new HashSet<>(orderMenus).size();
    }

    private boolean isAllBeverage(final List<OrderMenu> menus) {
        return menus.stream().allMatch(OrderMenu::isBeverage);
    }

    int totalQuantity(final List<OrderMenu> menuItems) {
        return Quantity.sum(menuItems, OrderMenu::getQuantity).getAmount();
    }

    Quantity count(final MenuCategory category) {
        return Quantity.sum(findBy(category), OrderMenu::getQuantity);
    }

    private List<OrderMenu> findBy(final MenuCategory category) {
        return menus.stream()
                .filter(orderMenu -> orderMenu.matchCategory(category))
                .toList();
    }
}
