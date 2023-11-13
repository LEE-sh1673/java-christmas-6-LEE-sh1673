package christmas.model.event;

import christmas.model.GiftMenus;
import christmas.model.Order;
import java.util.Collections;
import java.util.Map;

public class EventBenefits {

    private final Map<String, Long> benefits;

    private EventBenefits(final Map<String, Long> benefits) {
        this.benefits = benefits;
    }

    public static EventBenefits empty() {
        return new EventBenefits(Collections.emptyMap());
    }

    public static EventBenefits from(final Map<String, Long> benefits) {
        return new EventBenefits(benefits);
    }

    public EventBadge publishBadge() {
        return EventBadge.find(calculateTotalPrize());
    }

    public long calculateEstimatedPaymentPrize(final Order order, final GiftMenus giftMenus) {
        final long totalOrderPrize = order.calculateTotalPrize();
        final long totalDiscountPrize = calculateTotalDiscountPrize(giftMenus);
        return totalOrderPrize - totalDiscountPrize;
    }

    private long calculateTotalDiscountPrize(final GiftMenus giftMenus) {
        final long totalBenefitPrize = calculateTotalPrize();
        final long totalGiftMenuPrize = giftMenus.calculateTotalPrize();
        return totalBenefitPrize - totalGiftMenuPrize;
    }

    public long calculateTotalPrize() {
        return benefits.values()
                .stream()
                .reduce(0L, Long::sum);
    }

    public Map<String, Long> getBenefits() {
        return Collections.unmodifiableMap(benefits);
    }
}
