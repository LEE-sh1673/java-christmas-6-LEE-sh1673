package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;
import static christmas.exception.ErrorType.MAX_ORDER_EXCEEDED;
import static christmas.exception.ErrorType.ONLY_BEVERAGE_ORDERED;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderMenus {

    private static final int MAX_QUANTITY = 20;

    private final List<OrderMenu> menus;

    private OrderMenus(final List<String> namesWithQuantity) {
        this.menus = mapMenus(namesWithQuantity);
        validateDuplicate();
        validateSize();
        validateMenus();
    }

    public static OrderMenus byNamesWithQuantity(final List<String> namesWithQuantity) {
        return new OrderMenus(namesWithQuantity);
    }

    private List<OrderMenu> mapMenus(final List<String> namesWithQuantity) {
        return namesWithQuantity.stream()
                .map(OrderMenu::new)
                .toList();
    }

    private void validateDuplicate() {
        final Set<OrderMenu> uniqueMenus = new HashSet<>(menus);

        if (menus.size() != uniqueMenus.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateSize() {
        final int totalQuantity = calculateTotalQuantity();

        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(MAX_ORDER_EXCEEDED.getMessage());
        }
    }

    private int calculateTotalQuantity() {
        return menus.stream().mapToInt(OrderMenu::getQuantity).sum();
    }

    private void validateMenus() {
        if (isAllBeverage()) {
            throw new IllegalArgumentException(ONLY_BEVERAGE_ORDERED.getMessage());
        }
    }

    private boolean isAllBeverage() {
        return menus.stream().allMatch(menu -> menu.matchType(MenuType.BEVERAGE));
    }

    public long calculateTotalPrize() {
        return menus.stream()
                .mapToLong(OrderMenu::getPrize)
                .sum();
    }

    public int calculateQuantity(final MenuType menuType) {
        return menus.stream()
                .filter(menu -> menu.matchType(menuType))
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }
}
