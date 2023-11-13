package christmas.model.event;

public class EventDay {

    private final boolean isWeekend;

    public EventDay(final boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
