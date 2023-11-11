package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;

import java.util.List;
import java.util.regex.Pattern;

public class OrderMenus {

    private static final Pattern MENU_PATTERN = Pattern.compile("\\S+-[0-9]+");

    OrderMenus(final List<String> namesWithQuantity) {
        validateFormat(namesWithQuantity);
    }

    private void validateFormat(final List<String> namesWithQuantity) {
        validateNullOrEmpty(namesWithQuantity);
        for (final String name : namesWithQuantity) {
            if (!MENU_PATTERN.matcher(name).matches()) {
                throw new IllegalArgumentException(INVALID_ORDER.getMessage());
            }
        }
    }

    private void validateNullOrEmpty(final List<String> namesWithQuantity) {
        for (final String name : namesWithQuantity) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException(INVALID_ORDER.getMessage());
            }
        }
    }
}
