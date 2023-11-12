package christmas.model;

import java.util.List;

public class Order {

    private final OrderDate date;

    private final OrderMenus menus;

    public Order(final int day, final List<String> menuNames) {
        this.date = OrderDate.withDay(day);
        this.menus = new OrderMenus(menuNames);
    }

    public long calculateTotalPrize() {
        return menus.calculateTotalPrize();
    }
}
