package christmas.utils;


import java.util.List;

public class Parser {
    public static List<String> split(final String string, final String regex) {
        return List.of(
                string.split(regex, -1)
        );
    }

    private static List<Integer> parseStringListToIntegerList(List<String> strings){
        return strings.stream()
                .map(Integer::parseInt)
                .toList();
    }
}
