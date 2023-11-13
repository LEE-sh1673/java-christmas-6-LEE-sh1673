package christmas.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class EventCalendar {

    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    private final Map<Integer, EventDay> dates;

    public EventCalendar() {
        this.dates = new HashMap<>();
        for (int day = START_DAY; day <= END_DAY; day++) {
            final LocalDate localDate = LocalDate.of(YEAR, MONTH, day);
            final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            dates.put(day, new EventDay(isWeekend(dayOfWeek)));
        }
    }

    private boolean isWeekend(final DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekend(final int dayOfMonth) {
        return dates.get(dayOfMonth).isWeekend();
    }
}
