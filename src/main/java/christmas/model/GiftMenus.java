package christmas.model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class GiftMenus {

    private final EnumMap<GiftMenu, Long> menus;

    private GiftMenus(final EnumMap<GiftMenu, Long> menus) {
        this.menus = menus;
    }

    public static GiftMenus from(final Order order) {
        final EnumMap<GiftMenu, Long> menus = new EnumMap<>(GiftMenu.class);
        long totalPrize = order.calculateTotalPrize();

        for (final GiftMenu giftMenu : GiftMenu.values()) {
            if (giftMenu.check(totalPrize)) {
                menus.put(giftMenu, menus.getOrDefault(giftMenu, 0L) + 1);
            }
        }
        return new GiftMenus(menus);
    }

    public boolean exists() {
        return !menus.isEmpty();
    }

    public Map<GiftMenu, Long> getMenus() {
        return Collections.unmodifiableMap(menus);
    }
}
