package christmas.model;

import static java.util.stream.Collectors.toMap;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

enum Menu {

    MUSHROOM_SOUP("양송이수프", 6_000L, MenuCategory.APPETIZER),
    TAPAS("타파스", 5_500L, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000L, MenuCategory.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000L, MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", 54_000L, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000L, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000L, MenuCategory.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000L, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 5_000L, MenuCategory.DESSERT),

    ZERO_COKE("제로콜라", 3_000L, MenuCategory.BEVERAGE),
    RED_WINE("레드와인", 60_000L, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000L, MenuCategory.BEVERAGE);

    private final String name;

    private final long prize;

    private final MenuCategory category;

    private static final Map<String, Menu> nameToMenus = Arrays.stream(values()).collect(
            toMap(
                    menu -> menu.name,
                    menu -> menu
            )
    );

    Menu(final String name, final long prize, final MenuCategory category) {
        this.name = name;
        this.prize = prize;
        this.category = category;
    }

    static Menu findByName(final String name) {
        final Optional<Menu> menu = Optional.ofNullable(nameToMenus.get(name));

        if (menu.isEmpty()) {
            throw new OrderException(ErrorType.MENU_NON_EXISTS);
        }
        return menu.get();
    }

    boolean isBeverage() {
        return category == MenuCategory.BEVERAGE;
    }

    public long getPrize() {
        return prize;
    }
}
