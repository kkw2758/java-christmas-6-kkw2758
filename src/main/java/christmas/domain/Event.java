package christmas.domain;

public enum Event {
    WEEKDAY_SALE("평일 할인"),
    WEEKEND_SALE("주말 할인"),
    SPECIAL_SALE("특별 할인"),
    GIFT("증정 이벤트"),
    CHRISTMAS_SALE("크리스마스 디데이 할인");

    private final String name;

    Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
