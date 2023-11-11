package christmas.domain;


import christmas.domain.dto.OrderDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Orders {
    private final int MAX_MENU_COUNT = 20;
    private final Map<Menu, Integer> orderDetails = new EnumMap<>(Menu.class);


    private Orders(List<OrderDto> orders) {
        validateOrders(orders);
    }

    public static Orders of(List<OrderDto> orders) {
        return new Orders(orders);
    }

    private boolean hasDuplicatedMenu(List<OrderDto> orders) {
        return orders.stream().
                map(OrderDto::getName)
                .distinct()
                .count() != orders.size();
    }

    private void validateOrders(List<OrderDto> orders) {
        if (hasDuplicatedMenu(orders)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuCount(List<OrderDto> orders) {
        if (!checkMenuCount(orders)) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한번에 최대 20개까지만 주문할 수 있습니다.");
        }
    }

    private boolean checkMenuCount(List<OrderDto> orders) {
        return orders.stream()
                .mapToInt(OrderDto::getCount)
                .sum() <= MAX_MENU_COUNT;
    }
}
