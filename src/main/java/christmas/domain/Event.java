package christmas.domain;

public enum Event {
    WEEKDAY_SALE("평일 할인");

    private final String name;

    Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
