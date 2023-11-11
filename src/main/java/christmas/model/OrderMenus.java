package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;
import static christmas.exception.ErrorType.MAX_ORDER_EXCEEDED;
import static christmas.exception.ErrorType.ONLY_BEVERAGE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderMenus {

    private static final int MAX_QUANTITY = 20;

    private final List<OrderMenu> menus;

    OrderMenus(final List<String> namesWithQuantity) {
        this.menus = mapMenus(namesWithQuantity);
        validateDuplicate(menus);
        validateSize(menus);
        validateMenus(menus);
    }

    private List<OrderMenu> mapMenus(final List<String> namesWithQuantity) {
        return namesWithQuantity.stream()
                .map(OrderMenu::new)
                .toList();
    }

    private void validateDuplicate(final List<OrderMenu> menus) {
        final Set<OrderMenu> uniqueMenus = new HashSet<>(menus);

        if (menus.size() != uniqueMenus.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateSize(final List<OrderMenu> menus) {
        final int totalQuantity = calculateTotalQuantity(menus);

        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(MAX_ORDER_EXCEEDED.getMessage());
        }
    }

    private int calculateTotalQuantity(final List<OrderMenu> menus) {
        return menus.stream().mapToInt(OrderMenu::getQuantity).sum();
    }

    private void validateMenus(final List<OrderMenu> menus) {
        if (isAllBeverage(menus)) {
            throw new IllegalArgumentException(ONLY_BEVERAGE.getMessage());
        }
    }

    private static boolean isAllBeverage(final List<OrderMenu> menus) {
        return menus.stream().allMatch(OrderMenu::isBeverage);
    }

    public long calculateTotalPrize() {
        return menus.stream()
                .mapToLong(OrderMenu::getPrize)
                .sum();
    }
}
