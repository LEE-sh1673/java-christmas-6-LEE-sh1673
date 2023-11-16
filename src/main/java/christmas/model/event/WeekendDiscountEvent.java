package christmas.model.event;

import christmas.model.MenuType;
import christmas.model.Order;
import java.time.Month;
import java.time.Year;

public class WeekendDiscountEvent implements DiscountEvent {

    private static final String DISCOUNT_EVENT_NAME = "주말 할인";
    private static final int INITIAL_DISCOUNT_AMOUNT = 2023;

    private final EventCalendar calendar;

    public WeekendDiscountEvent(final EventCalendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String getName() {
        return DISCOUNT_EVENT_NAME;
    }

    @Override
    public DiscountPrice apply(final Order order) {
        final int day = order.getDay();

        if (!calendar.isWeekend(day)) {
            return DiscountPrice.ZERO;
        }
        final long numberOfMains = order.countQuantity(MenuType.MAIN);
        return DiscountPrice.of(INITIAL_DISCOUNT_AMOUNT * numberOfMains);
    }
}
