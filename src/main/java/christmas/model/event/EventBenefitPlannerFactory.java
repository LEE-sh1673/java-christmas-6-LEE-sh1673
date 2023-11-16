package christmas.model.event;

import java.time.Year;

public class EventBenefitPlannerFactory {

    private static final int YEAR = 2023;

    // 기본 생성자가 만들어지는 것을 막는다. (인스턴스화 방지용).
    private EventBenefitPlannerFactory() {
        throw new AssertionError();
    }

    public static EventBenefitPlanner createDecemberPlanner() {
        final EventCalendar calendar = createDecemberCalendar();
        final EventBenefitPlanner planner = new EventBenefitPlanner();

        planner.add(new DdayDiscountEvent());
        planner.add(new GiftMenuDiscountEvent());
        planner.add(new WeekdayDiscountEvent(calendar));
        planner.add(new WeekendDiscountEvent(calendar));
        planner.add(new SpecialDayDiscountEvent(calendar));

        return planner;
    }

    private static EventCalendar createDecemberCalendar() {
        return DecemberEventCalendar.withYear(Year.of(YEAR));
    }
}
