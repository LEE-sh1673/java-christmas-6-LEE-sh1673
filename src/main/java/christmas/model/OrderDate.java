package christmas.model;

import static christmas.exception.ErrorType.INVALID_DATE;

public class OrderDate {

    private static final int MIN_DAY = 1;
    private static final int NAX_DAY = 31;

    private final int day;

    public OrderDate(final int day) {
        validate(day);
        this.day = day;
    }

    private void validate(final int day) {
        if (day < MIN_DAY || day > NAX_DAY) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static OrderDate withDay(final int day) {
        return new OrderDate(day);
    }

    public int getDay() {
        return day;
    }
}
