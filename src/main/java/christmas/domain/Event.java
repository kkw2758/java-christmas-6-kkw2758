package christmas.domain;

public enum Event {
    WEEKDAY_SALE("평일 할인"),
    WEEKEND_SALE("주말 할인"),
    SPECIAL_SLAE("특별 할인");

    private final String name;

    Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
