package christmas.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class EventDay {

    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);

    private final LocalDate date;

    public EventDay(final LocalDate date) {
        this.date = date;
    }

    public boolean isWeekend() {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(date.getDayOfMonth());
    }
}
