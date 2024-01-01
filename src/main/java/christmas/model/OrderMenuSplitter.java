package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.regex.Pattern;

public class OrderMenuSplitter {

    private static final Pattern MENU_PATTERN = Pattern.compile("\\S+-\\d+");
    private static final String MENU_SPLITTER = "-";

    OrderMenu split(final String menu) {
        validate(menu);
        final String[] splitMenu = menu.split(MENU_SPLITTER);
        return new OrderMenu(splitMenu[0], Integer.parseInt(splitMenu[1]));
    }

    private void validate(final String menu) {
        if (menu == null || !MENU_PATTERN.matcher(menu).matches()) {
            throw new OrderException(ErrorType.INVALID_FORMAT_MENU);
        }
    }
}
