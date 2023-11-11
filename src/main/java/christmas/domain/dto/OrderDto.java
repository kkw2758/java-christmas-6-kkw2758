package christmas.domain.dto;


public class OrderDto {

    private final int count;
    private final String name;

    private OrderDto(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public static OrderDto of(String name, int count) {
        return new OrderDto(name, count);
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
