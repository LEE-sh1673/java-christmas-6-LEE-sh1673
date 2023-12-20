package christmas.model;

import christmas.exception.ErrorType;
import christmas.exception.OrderException;

class OrderDate {

    private static final int MIN = 1;
    private static final int MAX = 31;

    private final int dayOfMonth;

    private OrderDate(final int dayOfMonth) {
        if (dayOfMonth < MIN || dayOfMonth > MAX) {
            throw new OrderException(ErrorType.DATE_OUT_OR_RAGE);
        }
        this.dayOfMonth = dayOfMonth;
    }

    static OrderDate dayOfMonth(final int dayOfMonth) {
        return new OrderDate(dayOfMonth);
    }
}
