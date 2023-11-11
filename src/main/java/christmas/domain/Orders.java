package christmas.domain;


import christmas.domain.dto.Order;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Orders {

    private final Map<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);


    private Orders(List<Order> orders) {
        validateOrders(orders);
    }

    public static Orders of(List<Order> orders) {
        return new Orders(orders);
    }

    private boolean hasDuplicatedMenu(List<Order> orders) {
        return orders.stream().
                map(Order::getName)
                .distinct()
                .count() != orders.size();
    }

    private void validateOrders(List<Order> orders) {
        if (hasDuplicatedMenu(orders)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
