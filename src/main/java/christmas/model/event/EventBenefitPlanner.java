package christmas.model.event;

import christmas.model.Order;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventBenefitPlanner {

    private static final long MINIMUM_ORDER_PRIZE = 10_000L;

    private final Set<DiscountEvent> events;

    public EventBenefitPlanner() {
        this.events = new HashSet<>();
    }

    public void add(final DiscountEvent discountEvent) {
        events.add(discountEvent);
    }

    public EventBenefits plan(final Order order) {
        if (isLessThanMinimumPrize(order)) {
            return EventBenefits.empty();
        }
        return applyDiscountEvents(order);
    }

    private boolean isLessThanMinimumPrize(final Order order) {
        return order.calculateTotalPrize() < MINIMUM_ORDER_PRIZE;
    }

    private EventBenefits applyDiscountEvents(final Order order) {
        final Map<String, Long> benefits = new HashMap<>();

        for (final DiscountEvent event : events) {
            final DiscountPrice discountPrice = event.apply(order);

            if (!discountPrice.isZero()) {
                benefits.put(event.getName(), discountPrice.getAmount());
            }
        }
        return EventBenefits.from(benefits);
    }
}
