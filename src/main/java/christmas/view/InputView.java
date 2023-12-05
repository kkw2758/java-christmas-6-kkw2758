package christmas.view;

import christmas.exception.ErrorMessage;

public class InputView {
    private static final String NATURAL_NUMBER_REGULAR_EXPRESSION = "\\d+";

    public int requestVisitDate(){
        ConsoleWriter.printlnMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String userInput = ConsoleReader.enterMessage();
        validateNumber(userInput);
        return Integer.parseInt(userInput);
    }

    private static void validateNumber(String userInput) {
        if (isNotNumber(userInput)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VISIT_DATE_INPUT.getMessage());
        }
    }

    private static boolean isNotNumber(String userInput) {
        return !userInput.matches(NATURAL_NUMBER_REGULAR_EXPRESSION);
    }
}
