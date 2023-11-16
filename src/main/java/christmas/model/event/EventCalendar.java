package christmas.model.event;

public interface EventCalendar {

    boolean isWeekend(final int dayOfMonth);

    boolean isSpecialDay(final int dayOfMonth);
}
