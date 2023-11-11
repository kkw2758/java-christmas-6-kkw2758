package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.dto.OrderDto;
import christmas.domain.Orders;
import java.util.Arrays;

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

    public static Orders inputOrders() {
        return Orders.of(Arrays.stream(split(readLine(), ","))
                .map((member) -> split(member, "-"))
                .map(InputView::parseOrder)
                .toList());
    }

    private static OrderDto parseOrder(String[] orderInput) {
        validateOrderInput(orderInput);
        String name = orderInput[0].trim();
        int count = Integer.parseInt(orderInput[1].trim());
        return OrderDto.of(name, count);
    }

    private static String[] split(String userInput, String delimiter) {
        return userInput.split(delimiter);
    }

    private static void validateOrderInput(String[] orderInput) {
        validateOrderInputLength(orderInput);
        validateOrderInputFormat(orderInput);
    }

    private static void validateOrderInputFormat(String[] orderInput) {
        if (isNotNaturalNumber(orderInput[1])) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateOrderInputLength(String[] orderInput) {
        if (!checkOrderInputLength(orderInput)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean checkOrderInputLength(String[] orderInput) {
        return orderInput.length == 2;
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
