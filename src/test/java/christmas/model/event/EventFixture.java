package christmas.model.event;

import java.time.Year;

public class EventFixture {

    public static EventCalendar createDecemberCalendar() {
        return DecemberEventCalendar.withYear(Year.of(2023));
    }
}
