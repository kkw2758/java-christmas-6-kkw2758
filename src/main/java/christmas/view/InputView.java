package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String NATURAL_NUMBER_REGULAR_EXPRESSION = "\\d+";
    public static String readLine() {
        String userInput = Console.readLine();
        validateBlankInput(userInput);
        return userInput;
    }

    public static int inputNaturalNumber() {
        String userInput = readLine();
        validateNaturalNumber(userInput);
        return Integer.parseInt(userInput);
    }

    private static void validateNaturalNumber(String userInput) {
        if (isNotNaturalNumber(userInput)) {
            throw new IllegalArgumentException("자연수 형식으로 입력해주세요.");
        }
    }

    private static boolean isNotNaturalNumber(final String userInput) {
        return !userInput.matches(NATURAL_NUMBER_REGULAR_EXPRESSION);
    }

    private static void validateBlankInput(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백이 입력되었습니다.");
        }
    }
}
