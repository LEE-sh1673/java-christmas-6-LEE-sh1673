package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderMenus {

    private final List<OrderMenu> menus;

    OrderMenus(final List<String> namesWithQuantity) {
        this.menus = mapMenus(namesWithQuantity);
        validateDuplicate(menus);
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
}
