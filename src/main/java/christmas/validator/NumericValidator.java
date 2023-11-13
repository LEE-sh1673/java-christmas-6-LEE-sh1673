package christmas.validator;

import christmas.exception.ErrorType;
import java.util.regex.Pattern;

public class NumericValidator {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    public static void validate(final String stringNumber) {
        if (isNullOrEmpty(stringNumber) || !isNumeric(stringNumber)) {
            throw new IllegalArgumentException(ErrorType.INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    private static boolean isNullOrEmpty(final String stringNumber) {
        return stringNumber == null || stringNumber.isEmpty();
    }

    private static boolean isNumeric(final String stringNumber) {
        return NUMERIC_PATTERN.matcher(stringNumber).matches();
    }
}
