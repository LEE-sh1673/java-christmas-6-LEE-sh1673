package christmas.model.event;

public class EventBenefitPlannerFactory {

    // 기본 생성자가 만들어지는 것을 막는다. (인스턴스화 방지용).
    private EventBenefitPlannerFactory() {
        throw new AssertionError();
    }

    public static EventBenefitPlanner createDecemberPlanner() {
        final EventBenefitPlanner planner = new EventBenefitPlanner();

        planner.add(new DdayDiscountEvent());
        planner.add(new WeekdayDiscountEvent());
        planner.add(new WeekendDiscountEvent());
        planner.add(new SpecialDayDiscountEvent());
        planner.add(new GiftMenuDiscountEvent());

        return planner;
    }
}
