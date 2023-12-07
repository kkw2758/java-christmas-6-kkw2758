package christmas.domain;


import christmas.dto.request.OrderRequest;
import christmas.dto.response.OrderResponse;

public class Order {

    private final Menu menu;
    private final Count count;

    private Order(Menu menu, Count count) {
        this.menu = menu;
        this.count = count;
    }

    public static Order from(OrderRequest orderRequest) {
        return new Order(Menu.from(orderRequest.menu()), Count.from(orderRequest.count()));
    }

    public int calculatePrice() {
        return menu.getPrice() * count.getValue();
    }

    public Menu getMenu() {
        return menu;
    }

    public Count getCount() {
        return count;
    }

    public OrderResponse toResponse() {
        return new OrderResponse(menu.getName(), count.getValue());
    }
}
