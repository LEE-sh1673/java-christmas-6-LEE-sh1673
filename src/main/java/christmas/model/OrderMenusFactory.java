package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import christmas.utils.StringUtils;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class OrderMenusFactory {

    private static final String MENU_DELIMITER = ",";
    private static final String MENU_SPLITTER = "-";

    private static final Pattern MENU_PATTERN = Pattern.compile("\\S+-\\d+");

    public static List<OrderMenu> createMenus(final String menus) {
        final List<OrderMenu> orderMenus = createMenusFor(split(menus));

        if (isMenuDuplicated(orderMenus)) {
            throw new OrderException(ErrorType.MENU_DUPLICATES);
        }
        return orderMenus;
    }

    private static List<String> split(final String menus) {
        return StringUtils.split(menus, MENU_DELIMITER);
    }

    private static List<OrderMenu> createMenusFor(final List<String> menus) {
        return menus.stream()
                .map(OrderMenusFactory::createMenu)
                .toList();
    }

    private static OrderMenu createMenu(final String menu) {
        if (!MENU_PATTERN.matcher(menu).matches()) {
            throw new OrderException(ErrorType.INVALID_FORMAT_MENU);
        }
        final String[] splitMenu = menu.split(MENU_SPLITTER);
        return new OrderMenu(splitMenu[0], Integer.parseInt(splitMenu[1]));
    }

    private static boolean isMenuDuplicated(final List<OrderMenu> orderMenus) {
        return orderMenus.size() != new HashSet<>(orderMenus).size();
    }
}
