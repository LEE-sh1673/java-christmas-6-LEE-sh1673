package christmas.model.event;

import christmas.model.Order;

public class SpecialDayDiscountEvent implements DiscountEvent {

    private static final String DISCOUNT_EVENT_NAME = "특별 할인";
    private static final long INITIAL_DISCOUNT_AMOUNT = 1_000L;

    private final EventCalendar calendar;

    public SpecialDayDiscountEvent(final EventCalendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String getName() {
        return DISCOUNT_EVENT_NAME;
    }

    @Override
    public DiscountPrice apply(final Order order) {
        final int day = order.getDay();

        if (!calendar.isSpecialDay(day)) {
            return DiscountPrice.ZERO;
        }
        return DiscountPrice.of(INITIAL_DISCOUNT_AMOUNT);
    }
}
