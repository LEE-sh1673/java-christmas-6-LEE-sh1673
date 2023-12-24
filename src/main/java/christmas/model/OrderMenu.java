package christmas.model;

public class OrderMenu {

    private final Menu menu;
    private final Quantity quantity;

    OrderMenu(final String name, final int quantity) {
        this.menu = Menu.findByName(name);
        this.quantity = new Quantity(quantity);
    }

    boolean isBeverage() {
        return menu.isBeverage();
    }

    Quantity getQuantity() {
        return quantity;
    }
}
