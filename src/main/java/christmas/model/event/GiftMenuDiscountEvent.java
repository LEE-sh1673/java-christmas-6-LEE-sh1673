package christmas.model.event;

import christmas.model.GiftMenu;
import christmas.model.Order;

public class GiftMenuDiscountEvent implements DiscountEvent {

    private static final String DISCOUNT_EVENT_NAME = "증정 이벤트";

    private static final GiftMenu GIFT_MENU = GiftMenu.CHAMPAGNE;

    @Override
    public String getName() {
        return DISCOUNT_EVENT_NAME;
    }

    @Override
    public DiscountPrice apply(final Order order) {
        final long totalPrize = order.calculateTotalPrize();

        if (!GIFT_MENU.check(totalPrize)) {
            return DiscountPrice.ZERO;
        }
        return DiscountPrice.of(GIFT_MENU.getPrize());
    }
}
