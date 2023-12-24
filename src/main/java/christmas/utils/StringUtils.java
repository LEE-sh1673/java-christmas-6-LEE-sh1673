package christmas.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> split(final String text, final String delimiter) {
        if (isBlank(text)) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(text.split(delimiter)).toList();
    }

    public static boolean isBlank(final String text) {
        return text == null || text.isBlank();
    }
}
