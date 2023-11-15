package christmas.model.event;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EventCalendar {

    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);

    private final Map<Integer, EventDay> dates;

    public EventCalendar() {
        this.dates = new HashMap<>();
        for (int day = START_DAY; day <= END_DAY; day++) {
            dates.put(day, new EventDay(LocalDate.of(YEAR, MONTH, day)));
        }
    }

    public boolean isWeekend(final int dayOfMonth) {
        return dates.get(dayOfMonth).isWeekend();
    }

    public boolean isSpecialDay(final int dayOfMonth) {
        final int day = dates.get(dayOfMonth).getDayOfMonth();
        return SPECIAL_DAYS.contains(day);
    }
}
