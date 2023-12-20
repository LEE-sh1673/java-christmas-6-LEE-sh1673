package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class OrderMenuBoard {

    private static final String MENU_NAME_REGEX = "-[0-9]+,?";

    private static final String ORDER_MENU_REGEX = ",";

    OrderMenus select(final String menus) {
        validateDuplicates(menus);
        return new OrderMenus(makeMenus(menus));
    }

    private void validateDuplicates(final String menus) {
        final List<String> splitMenus = List.of(menus.split(MENU_NAME_REGEX));
        final Set<String> uniqueMenus = new HashSet<>(splitMenus);

        if (uniqueMenus.size() != splitMenus.size()) {
            throw new OrderException(ErrorType.MENU_DUPLICATES);
        }
    }

    private List<OrderMenu> makeMenus(final String menus) {
        final List<OrderMenu> orderMenus = Arrays.stream(menus.split(ORDER_MENU_REGEX))
                .map(OrderMenu::select)
                .toList();

        if (isAllBeverage(orderMenus)) {
            throw new OrderException(ErrorType.MENU_ONLY_BEVERAGE);
        }
        return orderMenus;
    }

    private boolean isAllBeverage(final List<OrderMenu> orderMenus) {
        return orderMenus.stream().allMatch(OrderMenu::isBeverage);
    }
}
