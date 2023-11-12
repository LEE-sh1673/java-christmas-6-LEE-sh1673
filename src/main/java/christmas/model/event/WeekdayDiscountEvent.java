package christmas.model.event;

import christmas.model.MenuType;
import christmas.model.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeekdayDiscountEvent implements DiscountEvent {

    private static final String DISCOUNT_EVENT_NAME = "평일 할인";
    private static final int INITIAL_DISCOUNT_AMOUNT = 2023;

    private final List<Integer> weekDays;

    public WeekdayDiscountEvent() {
        this.weekDays = mapWeekDays();
    }

    private List<Integer> mapWeekDays() {
        final List<Integer> weekDays = new ArrayList<>();

        for (int day = 3; day <= 31; day++) {
            final LocalDate date = LocalDate.of(2023, 12, day);

            if (!isWeekend(date)) {
                weekDays.add(day);
            }
        }
        return weekDays;
    }

    private boolean isWeekend(final LocalDate date) {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    @Override
    public String getName() {
        return DISCOUNT_EVENT_NAME;
    }

    @Override
    public DiscountPrice apply(final Order order) {
        final int day = order.getDay();

        if (!weekDays.contains(day)) {
            return DiscountPrice.ZERO;
        }
        final long numberOfDesserts = order.calculateQuantity(MenuType.DESSERT);
        return DiscountPrice.of(INITIAL_DISCOUNT_AMOUNT * numberOfDesserts);
    }
}
