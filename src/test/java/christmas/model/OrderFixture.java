package christmas.model;

public class OrderFixture {

    public static Order createOrder(final String day, final String namesWithQuantity) {
        return new Order(
                OrderDate.withDay(day),
                OrderMenus.byNamesWithQuantity(namesWithQuantity)
        );
    }

    public static OrderMenus createMenus(final String namesWithQuantity) {
        return OrderMenus.byNamesWithQuantity(namesWithQuantity);
    }
}
