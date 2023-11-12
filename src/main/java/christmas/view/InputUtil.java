package christmas.view;

import java.util.function.Supplier;

public class InputUtil {
    public static <T> T retryOnException(Supplier<T> supplier, boolean lineBreak) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                if (lineBreak) {
                    System.out.println();
                }
            }
        }
    }
}
