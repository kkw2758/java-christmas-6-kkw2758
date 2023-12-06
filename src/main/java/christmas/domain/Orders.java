package christmas.domain;

import christmas.dto.request.OrdersRequest;
import christmas.exception.ErrorMessage;
import christmas.utils.Validator;
import java.util.List;

public class Orders {
    private static final int MIN_TOTAL_MENU_COUNT = 1;
    private static final int MAX_TOTAL_MENU_COUNT = 20;
    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(OrdersRequest ordersRequest) {
        List<Order> orders = ordersRequest.orders().stream()
                .map(Order::from)
                .toList();
        validateOrders(orders, ErrorMessage.INVALID_ORDERS);
        return new Orders(orders);
    }

    private static void validateOrders(List<Order> orders, ErrorMessage e) {
        Validator.validateNoDuplicates(convertOrdersToMenu(orders), e);
        validateNotOnlyDrink(orders, e);
        Validator.validateInRange(calculateTotalMenuCount(orders), MIN_TOTAL_MENU_COUNT, MAX_TOTAL_MENU_COUNT, e);
    }

    private static void validateNotOnlyDrink(List<Order> orders, ErrorMessage e) {
        if (isOrdersHasOnlyDrink(orders)){
            e.throwException();
        }
    }

    private static boolean isOrdersHasOnlyDrink(List<Order> orders) {
        return convertOrdersToMenuCategories(orders).equals(List.of(MenuCategory.DRINK));
    }

    private static int calculateTotalMenuCount(List<Order> orders) {
        return orders.stream()
                .map(Order::getCount)
                .map(Count::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }
    private static List<MenuCategory> convertOrdersToMenuCategories(List<Order> orders) {
        return orders.stream()
                .map(Order::getMenu)
                .map(Menu::getCategory)
                .distinct()
                .toList();
    }

    private static List<Menu> convertOrdersToMenu(List<Order> orders) {
        return orders.stream()
                .map(Order::getMenu)
                .toList();
    }

    public int calculateTotalPriceBeforeSale() {
        return orders.stream()
                .map(Order::calculatePrice)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
