package christmas.utils;

import christmas.exception.ErrorMessage;
import java.util.List;

public class Converter {

    public static List<String> convertToList(String target) {
        return target.chars()
                .mapToObj(Character::toString)
                .toList();
    }

    public static long toLong(final String input, final ErrorMessage e) {
        Validator.validateNumberFormat(input, e);
        return Long.parseLong(input);
    }

    public static int toInt(final String input, final ErrorMessage e) {
        Validator.validateNumberFormat(input, e);
        return Integer.parseInt(input);
    }
}
