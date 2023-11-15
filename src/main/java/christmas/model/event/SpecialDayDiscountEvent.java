package christmas.model.event;

import christmas.model.Order;
import java.time.Month;
import java.time.Year;

public class SpecialDayDiscountEvent implements DiscountEvent {

    private static final String DISCOUNT_EVENT_NAME = "특별 할인";
    private static final long INITIAL_DISCOUNT_AMOUNT = 1_000L;

    private final EventCalendar calendar;

    public SpecialDayDiscountEvent() {
        this.calendar = EventCalendar.of(Year.of(2023), Month.DECEMBER);
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
