package christmas.model;

import static christmas.exception.ErrorType.INVALID_DATE;

import java.util.regex.Pattern;

public class OrderDate {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    private static final int MIN_DAY = 1;
    private static final int NAX_DAY = 31;

    private final int day;

    public OrderDate(final String day) {
        validateNumeric(day);
        this.day = Integer.parseInt(day);
        validateRange();
    }

    public static void validateNumeric(final String strDay) {
        if (isNullOrEmpty(strDay) || !isNumeric(strDay)) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    private static boolean isNullOrEmpty(final String stringNumber) {
        return stringNumber == null || stringNumber.isEmpty();
    }

    private static boolean isNumeric(final String stringNumber) {
        return NUMERIC_PATTERN.matcher(stringNumber).matches();
    }

    private void validateRange() {
        if (day < MIN_DAY || day > NAX_DAY) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static OrderDate withDay(final String day) {
        return new OrderDate(day);
    }

    public int getDay() {
        return day;
    }
}
