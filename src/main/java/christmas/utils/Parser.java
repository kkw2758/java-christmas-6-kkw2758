package christmas.utils;


import christmas.dto.request.OrderRequest;
import christmas.dto.request.OrdersRequest;
import java.util.List;

public class Parser {
    private static final String COMMA_DELIMITER = ",";
    private static final String DASH_DELIMITER = "-";
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    public static List<String> split(final String string, final String regex) {
        return List.of(
                string.split(regex, -1)
        );
    }

    public static OrdersRequest generateOrdersRequest(String orders) {
        return new OrdersRequest(Parser.split(orders, COMMA_DELIMITER).stream()
                .map(Parser::generateOrderRequest)
                .toList());
    }

    private static OrderRequest generateOrderRequest(String order) {
        String menu = Parser.split(order, DASH_DELIMITER).get(MENU_INDEX);
        String count = Parser.split(order, DASH_DELIMITER).get(COUNT_INDEX);
        return new OrderRequest(menu, Integer.parseInt(count));
    }
}
