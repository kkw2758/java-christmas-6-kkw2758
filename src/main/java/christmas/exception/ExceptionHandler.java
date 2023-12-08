package christmas.exception;

import java.util.function.Supplier;

public class ExceptionHandler {
    public static <T> T handle(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
            return handle(supplier);
        }
    }

    private static void printExceptionMessage(final IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }
}
