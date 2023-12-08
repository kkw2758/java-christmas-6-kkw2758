package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.request.OrdersRequest;
import christmas.exception.ErrorMessage;
import christmas.utils.Converter;
import christmas.utils.Parser;
import christmas.utils.Validator;

public class Inputview {
    private static final Inputview instance = new Inputview();

    private Inputview() {
    }

    public static Inputview getInstance() {
        return instance;
    }

    public static String enterMessage(ErrorMessage e) {
        String userInput = Console.readLine();
        Validator.validateBlankInput(userInput, e);
        return userInput;
    }

    public int requestVisitDate(ErrorMessage e) {
        OutputView.printlnMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String userInput = enterMessage(e);
        return Converter.toInt(userInput, e);
    }

    public OrdersRequest requestOrders(ErrorMessage e) {
        OutputView.printlnMessage("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Parser.toOrdersRequest(enterMessage(e), e);
    }
}
