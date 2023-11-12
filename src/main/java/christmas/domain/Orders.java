package christmas.domain;


import christmas.domain.dto.OrderDto;
import christmas.exception.ErrorMessage;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Orders {
    private final int MAX_MENU_COUNT = 20;
    private final Map<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);


    private Orders(List<OrderDto> orders) {
        validateOrders(orders);
        initOrders(orders);
    }

    public static Orders of(List<OrderDto> orders) {
        return new Orders(orders);
    }

    private void initOrders(List<OrderDto> orders) {
        for(OrderDto order : orders) {
            orderDetails.put(Menu.findMenuByName(order.getName().getValue()), order.getCount().getValue());
        }
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
                .count();
    }

    private void validateOrders(List<OrderDto> orders) {
        validateMenuCount(orders);
        validateDuplicateMenu(orders);
        validateOnlyDrink(orders);
    }

    private void validateOnlyDrink(List<OrderDto> orders) {
        if (checkOnlyDrink(orders)) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_DRINK_ORDER_ERROR.getMessage());
        }
    }

    private boolean checkOnlyDrink(List<OrderDto> orders) {
        return orders.stream()
                .map((order) -> Menu.findMenuByName(order.getName().getValue()))
                .map(Menu::getCategory)
                .distinct()
                .toList()
                .equals(List.of(Category.DRINK));
    }

    private void validateDuplicateMenu(List<OrderDto> orders) {
        if (hasDuplicatedMenu(orders)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }

    private boolean hasDuplicatedMenu(List<OrderDto> orders) {
        return orders.stream().
                map(OrderDto::getName)
                .map(Name::getValue)
                .distinct()
                .count() != orders.size();
    }

    private void validateMenuCount(List<OrderDto> orders) {
        if (!checkMenuCount(orders)) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.MAX_MENU_COUNT_ERROR.getMessage(), MAX_MENU_COUNT));
        }
    }

    private boolean checkMenuCount(List<OrderDto> orders) {
        return orders.stream()
                .map(OrderDto::getCount)
                .mapToInt(Count::getValue)
                .sum() <= MAX_MENU_COUNT;
    }
}
