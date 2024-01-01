package christmas.model;

import christmas.utils.StringUtils;
import java.util.List;

public class OrderMenusFactory {

    private static final String MENU_DELIMITER = ",";

    private final OrderMenuSplitter menuSplitter;

    public OrderMenusFactory(final OrderMenuSplitter menuSplitter) {
        this.menuSplitter = menuSplitter;
    }

    public OrderMenus createMenus(final String menus) {
        return orderMenus(split(menus));
    }

    private List<OrderMenu> split(final String menus) {
        return StringUtils.split(menus, MENU_DELIMITER)
                .stream()
                .map(menuSplitter::split)
                .toList();
    }

    private OrderMenus orderMenus(final List<OrderMenu> menus) {
        return new OrderMenus(menus);
    }
}
