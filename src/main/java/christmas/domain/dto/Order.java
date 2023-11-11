package christmas.domain.dto;


public class Order {

    private final int count;
    private final String name;

    private Order(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public static Order of(String name, int count) {
        return new Order(name, count);
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
