package christmas.utils;

import java.util.List;

public class Parser {
    public static List<String> split(final String string, final String regex) {
        return List.of(
                string.split(regex, -1)
        );
    }
}
