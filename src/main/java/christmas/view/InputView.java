package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.request.OrderRequest;
import christmas.dto.request.OrdersRequest;
import christmas.exception.ErrorMessage;
import christmas.utils.Parser;
import christmas.utils.Validator;

public class InputView {
    private static final String COMMA_DELIMITER = ",";
    private static final String DASH_DELIMITER = "-";
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final int ORDER_SIZE = 2;

    private static final InputView instance = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public int requestVisitDate(){
        ConsoleWriter.printlnMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String userInput = Console.readLine();
        validateVisitDate(userInput, ErrorMessage.INVALID_VISIT_DATE_INPUT);
        return Integer.parseInt(userInput);
    }

    private void validateVisitDate(String userInput, ErrorMessage e) {
        Validator.validateBlankInput(userInput, e);
        Validator.validateNumberFormat(userInput, e);
    }

    public OrdersRequest requestOrders() {
        ConsoleWriter.printlnMessage("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String userInput = Console.readLine();
        validateOrdersInput(userInput, ErrorMessage.INVALID_ORDERS);
        return generateOrdersRequest(userInput);
    }

    private OrdersRequest generateOrdersRequest(String orders) {
        return new OrdersRequest(Parser.split(orders, COMMA_DELIMITER).stream()
                .map(this::generateOrderRequest)
                .toList());
    }

    private OrderRequest generateOrderRequest(String order) {
        String menu = Parser.split(order, DASH_DELIMITER).get(MENU_INDEX);
        String count = Parser.split(order, DASH_DELIMITER).get(COUNT_INDEX);
        return new OrderRequest(menu, Integer.parseInt(count));
    }

    private static void validateOrdersInput(String ordersInput, ErrorMessage e) {
        Validator.validateBlankInput(ordersInput, e);
        for (String order : Parser.split(ordersInput, COMMA_DELIMITER)) {
            validateOrder(order, e);
        }
    }

    private static void validateOrder(String order, ErrorMessage e) {
        Validator.validateSize(Parser.split(order, DASH_DELIMITER), ORDER_SIZE, e);
        Validator.validateNumberFormat(Parser.split(order, DASH_DELIMITER).get(COUNT_INDEX), e);
    }
}
