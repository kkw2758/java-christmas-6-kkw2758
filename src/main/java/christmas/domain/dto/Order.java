package christmas.domain.dto;

import christmas.domain.Count;
import christmas.domain.Name;

public class Order {

    private final Count count;
    private final Name name;

    private Order(Name name, Count count) {
        this.name = name;
        this.count = count;
    }

    public static Order of(Name name, Count count) {
        return new Order(name, count);
    }

    public Count getCount() {
        return count;
    }

    public Name getName() {
        return name;
    }
}
