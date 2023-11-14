package christmas.model.event;

import christmas.model.Order;

public class DdayDiscountEvent implements DiscountEvent {

    private static final String DISCOUNT_EVENT_NAME = "크리스마스 디데이 할인";
    private static final int MAX_DAY = 25;
    private static final long INITIAL_AMOUNT = 1_000L;
    private static final long INCREASE_AMOUNT_UNIT = 100L;

    @Override
    public String getName() {
        return DISCOUNT_EVENT_NAME;
    }

    @Override
    public DiscountPrice apply(final Order order) {
        final int day = order.getDay();

        if (day > MAX_DAY) {
            return DiscountPrice.ZERO;
        }
        return DiscountPrice.of(calculateTotalPrize(day));
    }

    private long calculateTotalPrize(final int day) {
        return INITIAL_AMOUNT + (day - 1) * INCREASE_AMOUNT_UNIT;
    }
}
