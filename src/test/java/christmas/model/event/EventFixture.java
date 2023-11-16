package christmas.model.event;

import java.time.Year;

public class EventFixture {

    public static EventBenefitPlanner createDecemberPlanner() {
        final EventBenefitPlanner planner = new EventBenefitPlanner();
        final EventCalendar calendar = createDecemberCalendar();

        planner.add(new DdayDiscountEvent());
        planner.add(new WeekdayDiscountEvent(calendar));
        planner.add(new WeekendDiscountEvent(calendar));
        planner.add(new SpecialDayDiscountEvent(calendar));
        planner.add(new GiftMenuDiscountEvent());

        return planner;
    }

    public static EventCalendar createDecemberCalendar() {
        return DecemberEventCalendar.withYear(Year.of(2023));
    }
}
