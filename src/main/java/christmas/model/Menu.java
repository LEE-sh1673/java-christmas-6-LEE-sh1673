package christmas.model;

import static java.util.stream.Collectors.toMap;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6_000L),
    TAPAS("타파스", 5_500L),
    CAESAR_SALAD("시저샐러드", 8_000L),

    T_BONE_STEAK("티본스테이크", 55_000L),
    BBQ_RIBS("바비큐립", 54_000L),
    SEAFOOD_PASTA("해산물파스타", 35_000L),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000L),

    CHOCOLATE_CAKE("초코케이크", 15_000L),
    ICE_CREAM("아이스크림", 5_000L),

    ZERO_COKE("제로콜라", 3_000L),
    RED_WINE("레드와인", 60_000L),
    CHAMPAGNE("샴페인", 25_000L);

    private final String name;

    private final long prize;

    private static final Map<String, Menu> nameToMenus = Arrays.stream(values()).collect(
            toMap(
                    menu -> menu.name,
                    menu -> menu
            )
    );

    Menu(final String name, final long prize) {
        this.name = name;
        this.prize = prize;
    }

    static Menu findByName(final String name) {
        final Optional<Menu> menu = Optional.ofNullable(nameToMenus.get(name));

        if (menu.isEmpty()) {
            throw new OrderException(ErrorType.MENU_NON_EXISTS);
        }
        return menu.get();
    }
}
