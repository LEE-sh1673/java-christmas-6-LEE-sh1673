package christmas.model;

import java.util.function.Predicate;

public enum GiftMenu {

    CHAMPAGNE(Menu.CHAMPAGNE, (prize) -> prize >= 120_000);

    private final Menu menu;

    private final Predicate<Long> condition;

    GiftMenu(final Menu menu, final Predicate<Long> condition) {
        this.menu = menu;
        this.condition = condition;
    }

    public boolean check(final long prize) {
        return condition.test(prize);
    }

    public String getName() {
        return menu.getName();
    }

    public long getPrize() {
        return menu.getPrize();
    }
}
