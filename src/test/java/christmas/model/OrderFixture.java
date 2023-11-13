package christmas.model;

import java.util.Arrays;
import java.util.List;

public class OrderFixture {

    public static Order createOrder(final String day, final List<String> namesWithQuantity) {
        return new Order(
                OrderDate.withDay(day),
                OrderMenus.byNamesWithQuantity(namesWithQuantity)
        );
    }

    public static OrderMenus createMenus(final String... namesWithQuantity) {
        return OrderMenus.byNamesWithQuantity(Arrays.asList(namesWithQuantity));
    }
}
