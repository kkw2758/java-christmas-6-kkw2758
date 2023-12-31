package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;

public class InputView {
    private static final String NATURAL_NUMBER_REGULAR_EXPRESSION = "\\d+";

    public static String readLine() {
        String userInput = Console.readLine();
        validateOrdersBlankInput(userInput);
        return userInput;
    }

    public static int inputNaturalNumber() {
        String userInput = Console.readLine();
        validateDayBlankInput(userInput);
        validateNaturalNumber(userInput);
        return Integer.parseInt(userInput);
    }

    private static void validateNaturalNumber(String userInput) {
        if (isNotNaturalNumber(userInput)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY_OF_MONTH_ERROR.getMessage());
        }
    }

    private static boolean isNotNaturalNumber(final String userInput) {
        return !userInput.matches(NATURAL_NUMBER_REGULAR_EXPRESSION);
    }

    private static void validateDayBlankInput(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY_OF_MONTH_ERROR.getMessage());
        }
    }
    private static void validateOrdersBlankInput(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }
}
