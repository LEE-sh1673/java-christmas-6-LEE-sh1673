package christmas.model.event;

import static christmas.exception.ErrorType.INVALID_DATE;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.IntStream;

public final class DecemberEventCalendar implements EventCalendar {

    private static final int MIN_DAY = 1;
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);

    private final YearMonth yearMonth;

    private final List<EventDay> days;

    private DecemberEventCalendar(final YearMonth yearMonth, final List<LocalDate> days) {
        this.yearMonth = yearMonth;
        this.days = makeCalendar(days);
    }

    private List<EventDay> makeCalendar(final List<LocalDate> days) {
        return days.stream()
                .map(EventDay::new)
                .toList();
    }

    public static DecemberEventCalendar withYear(final Year year) {
        final YearMonth yearMonth = YearMonth.of(year.getValue(), Month.DECEMBER);
        final int yearValue = yearMonth.getYear();
        final int monthValue = yearMonth.getMonthValue();

        final List<LocalDate> days = IntStream.rangeClosed(MIN_DAY, yearMonth.lengthOfMonth())
                .mapToObj(day -> LocalDate.of(yearValue, monthValue, day))
                .toList();
        return new DecemberEventCalendar(yearMonth, days);
    }

    @Override
    public boolean isWeekend(final int dayOfMonth) {
        validateDayInMonth(dayOfMonth);
        return days.get(dayOfMonth - 1).isWeekend();
    }

    @Override
    public boolean isSpecialDay(final int dayOfMonth) {
        validateDayInMonth(dayOfMonth);
        final int day = days.get(dayOfMonth - 1).getDayOfMonth();
        return SPECIAL_DAYS.contains(day);
    }

    private void validateDayInMonth(final int dayOfMonth) {
        if (dayOfMonth > yearMonth.lengthOfMonth() || dayOfMonth < MIN_DAY) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }
}
