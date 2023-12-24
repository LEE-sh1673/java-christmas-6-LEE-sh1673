package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;

class Quantity {

    private static final int MIN = 1;

    private final int amount;

    Quantity(final int amount) {
        if (amount < MIN) {
            throw new OrderException(ErrorType.INVALID_ORDER_QUANTITY);
        }
        this.amount = amount;
    }

    int getAmount() {
        return amount;
    }
}
