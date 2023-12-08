package christmas.domain;

import christmas.dto.request.OrdersRequest;
import christmas.exception.ErrorMessage;
import christmas.utils.Validator;
import java.util.List;

public class Orders {

    private static final int MIN_ORDERS_COUNT = 1;
    private static final int MAX_ORDERS_COUNT = 20;
    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(OrdersRequest ordersRequest) {
        List<Order> orders = ordersRequest.orderRequests().stream()
                .map(Order::from)
                .toList();
        Validator.validateNoDuplicates(orders, ErrorMessage.INVALID_ORDERS);
        validateNotOnlyDrink(orders, ErrorMessage.INVALID_ORDERS);
        Validator.validateInRange(calculateMenuCount(orders), MIN_ORDERS_COUNT, MAX_ORDERS_COUNT,
                ErrorMessage.INVALID_ORDERS);
        return new Orders(orders);
    }

    private static void validateNotOnlyDrink(List<Order> orders, ErrorMessage e) {
        if (getCategories(orders).equals(List.of(Category.DRINK))) {
            e.throwException();
        }
    }

    private static int calculateMenuCount(List<Order> orders) {
        return (int) orders.stream()
                .mapToInt(order -> order.getCount().getValue())
                .sum();
    }

    private static List<Category> getCategories(List<Order> orders) {
        return orders.stream()
                .map(Order::getMenu)
                .map(Menu::getCategory)
                .distinct()
                .toList();
    }

    public int calculateTotalPriceBeforeSale() {
        return orders.stream()
                .mapToInt(Order::calculateOrderPrice)
                .sum();
    }

    public int getSpecificCategoryCount(Category category) {
        return orders.stream()
                .filter(order -> order.getMenu().getCategory().equals(category))
                .mapToInt(order -> order.getCount().getValue())
                .sum();
    }

    public boolean hasSpecificCategory(Category category) {
        return !orders.stream()
                .map(Order::getMenu)
                .filter(menu -> menu.getCategory().equals(category))
                .toList()
                .isEmpty();
    }
}
