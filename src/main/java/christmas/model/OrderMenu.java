package christmas.model;

public class OrderMenu {

    private final Menu menu;
    private final Quantity quantity;

    OrderMenu(final String name, final int quantity) {
        this(Menu.findByName(name), new Quantity(quantity));
    }

    OrderMenu(final Menu menu, final Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    boolean isBeverage() {
        return matchCategory(MenuCategory.BEVERAGE);
    }

    boolean matchCategory(final MenuCategory category) {
        return menu.matchCategory(category);
    }

    Quantity getQuantity() {
        return quantity;
    }
}
