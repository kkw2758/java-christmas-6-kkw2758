package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleReader {
    public static String enterMessage() {
        return Validator.validate(Console.readLine());
    }

    private static class Validator {
        public static String validate(String message) {
            validateBlankInput(message);
            return message;
        }

        private static void validateBlankInput(String message) {
            if (message.isBlank()) {
                throw new IllegalArgumentException();
            }
        }
    }
}
