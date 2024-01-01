package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;
import java.util.Collection;
import java.util.function.Function;

class Quantity {

    private static final Quantity ONE = new Quantity(1);

    private static final int MIN = 1;

    private final int amount;

    Quantity(final int amount) {
        if (amount < MIN) {
            throw new OrderException(ErrorType.INVALID_ORDER_QUANTITY);
        }
        this.amount = amount;
    }

    static <T> Quantity sum(final Collection<T> bags, final Function<T, Quantity> mapper) {
        return new Quantity(totalAmount(bags, mapper));
    }

    private static <T> int totalAmount(
            final Collection<T> bags,
            final Function<T, Quantity> mapper) {

        return bags.stream()
                .mapToInt(t -> mapper.apply(t).getAmount())
                .sum();
    }

    int getAmount() {
        return amount;
    }
}
