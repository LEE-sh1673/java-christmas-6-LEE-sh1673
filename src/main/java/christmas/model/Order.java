package christmas.model;

import java.util.List;

public class Order {

    private final OrderDate date;

    private final OrderMenus menus;

    public Order(final OrderDate date, final OrderMenus menus) {
        this.date = date;
        this.menus = menus;
    }

    public long calculateTotalPrize() {
        return menus.calculateTotalPrize();
    }

    public int getDay() {
        return date.getDay();
    }

    public long calculateQuantity(final MenuType menuType) {
        return menus.calculateQuantity(menuType);
    }

    public List<OrderMenu> getMenus() {
        return menus.getMenus();
    }
}
