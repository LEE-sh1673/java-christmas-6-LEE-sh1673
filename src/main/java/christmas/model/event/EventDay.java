package christmas.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class EventDay {

    private final LocalDate date;

    public EventDay(final LocalDate date) {
        this.date = date;
    }

    public boolean isWeekend() {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }
}
