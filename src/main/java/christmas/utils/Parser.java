package christmas.utils;

import christmas.dto.request.OrderRequest;
import christmas.dto.request.OrdersRequest;
import christmas.exception.ErrorMessage;
import java.util.List;

public class Parser {
    private static final int ORDER_MENU_INDEX = 0;
    private static final int ORDER_COUNT_INDEX = 1;
    private static final int ORDER_SIZE = 2;
    private static final String COMMA_DELIMITER = ",";
    private static final String DASH_DELIMITER = "-";

    public static List<String> split(final String string, final String regex) {
        return List.of(
                string.split(regex, -1)
        );
    }

    public static OrdersRequest toOrdersRequest(String ordersString, ErrorMessage e) {
        List<String> splittedOrders = split(ordersString, COMMA_DELIMITER);
        List<OrderRequest> orderRequests = splittedOrders.stream()
                .map(s -> toOrderRequest(s, e))
                .toList();
        return new OrdersRequest(orderRequests);
    }

    private static OrderRequest toOrderRequest(String orderString, ErrorMessage e) {
        List<String> splittedOrder = split(orderString, DASH_DELIMITER);
        Validator.validateSize(splittedOrder, ORDER_SIZE, e);
        return new OrderRequest(splittedOrder.get(ORDER_MENU_INDEX),
                Converter.toInt(splittedOrder.get(ORDER_COUNT_INDEX), e));
    }
}
