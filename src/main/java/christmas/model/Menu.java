package christmas.model;

import static christmas.exception.ErrorType.INVALID_ORDER;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6_000L, MenuType.APPETIZER),
    TAPAS("타파스", 5_500L, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000L, MenuType.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000L, MenuType.MAIN),
    BBQ_RIBS("바비큐립", 54_000L, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000L, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000L, MenuType.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000L, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000L, MenuType.DESSERT),

    ZERO_COKE("제로콜라", 3_000L, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60_000L, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000L, MenuType.BEVERAGE);

    private static final Map<String, Menu> nameToMenus = Arrays.stream(values()).collect(
            toMap(
                    menu -> menu.name,
                    menu -> menu
            )
    );

    private final String name;

    private final long price;

    private final MenuType menuType;

    Menu(final String name, final long price, final MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Menu findMenu(final String menuName) {
        final Optional<Menu> menu = Optional.ofNullable(nameToMenus.get(menuName));

        if (menu.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
        return menu.get();
    }

    public String getName() {
        return name;
    }

    public long getPrize() {
        return price;
    }

    public boolean matchType(final MenuType menuType) {
        return this.menuType == menuType;
    }
}
