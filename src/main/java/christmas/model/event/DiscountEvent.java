package christmas.model.event;

import christmas.model.Order;

public interface DiscountEvent {

    String getName();

    DiscountPrice apply(final Order order);
}
