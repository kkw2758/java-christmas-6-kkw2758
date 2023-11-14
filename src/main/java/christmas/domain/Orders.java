package christmas.domain;


import christmas.exception.ErrorMessage;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Orders {
    private final int MAX_MENU_COUNT = 20;
    private static final String DELIMITER = ",";
    private final Map<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);

    private Orders(List<Order> orders) {
        validateOrders(orders);
        initOrders(orders);
    }

    public static Orders of(String orders) {
        return new Orders(stringToOrderList(orders));
    }

    private static List<Order> stringToOrderList(String orderInput) {
        return Arrays.stream(orderInput.split(DELIMITER))
                .map(String::trim)
                .map(Order::of)
                .toList();
    }

    private void initOrders(List<Order> orders) {
        for(Order order : orders) {
            orderDetails.put(Menu.findMenuByName(order.getName().getValue()), order.getCount().getValue());
        }
    }

    public List<Menu> getOrderedMenus() {
        return orderDetails.keySet().stream()
                .toList();
    }

    public int getOrderCountWithMenu(Menu menu) {
        return orderDetails.get(menu);
    }

    public int calculatePriceBeforeSale() {
        return orderDetails.keySet().stream()
                .map((menu) -> menu.getPrice() * orderDetails.get(menu))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateCategoryCount(Category category) {
        return (int) orderDetails.keySet().stream()
                .filter((menu) -> menu.getCategory().equals(category))
                .map(orderDetails::get)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateOrders(List<Order> orders) {
        validateMenuCount(orders);
        validateDuplicateMenu(orders);
        validateOnlyDrink(orders);
    }

    private void validateOnlyDrink(List<Order> orders) {
        if (checkOnlyDrink(orders)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK_ORDER_ERROR.getMessage());
        }
    }

    private boolean checkOnlyDrink(List<Order> orders) {
        return orders.stream()
                .map((order) -> Menu.findMenuByName(order.getName().getValue()))
                .map(Menu::getCategory)
                .distinct()
                .toList()
                .equals(List.of(Category.DRINK));
    }

    private void validateDuplicateMenu(List<Order> orders) {
        if (hasDuplicatedMenu(orders)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }

    private boolean hasDuplicatedMenu(List<Order> orders) {
        return orders.stream().
                map(Order::getName)
                .map(Name::getValue)
                .distinct()
                .count() != orders.size();
    }

    private void validateMenuCount(List<Order> orders) {
        if (!checkMenuCount(orders)) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.MAX_MENU_COUNT_ERROR.getMessage(), MAX_MENU_COUNT));
        }
    }

    private boolean checkMenuCount(List<Order> orders) {
        return orders.stream()
                .map(Order::getCount)
                .mapToInt(Count::getValue)
                .sum() <= MAX_MENU_COUNT;
    }
}
