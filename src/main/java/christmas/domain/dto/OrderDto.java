package christmas.domain.dto;


import christmas.domain.Count;
import christmas.domain.Name;

public class OrderDto {
    private final Count count;
    private final Name name;

    private OrderDto(Name name, Count count) {
        this.name = name;
        this.count = count;
    }

    public static OrderDto of(Name name, Count count) {
        return new OrderDto(name, count);
    }

    public Count getCount() {
        return count;
    }

    public Name getName() {
        return name;
    }
}
