package christmas.domain.event;

import christmas.domain.Category;
import christmas.domain.Day;
import christmas.domain.Orders;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum SaleEvent implements Event{

    WEEKDAY_SALE("평일 할인",
            (orders, day) -> !day.isWeekend() && orders.calculateCategoryCount(Category.DESSERT) != 0,
            (orders, day) -> orders.calculateCategoryCount(Category.DESSERT) * 2023
    ),
    WEEKEND_SALE("주말 할인",
            (orders, day) -> day.isWeekend() && orders.calculateCategoryCount(Category.MAIN) != 0,
            (orders, day) -> orders.calculateCategoryCount(Category.MAIN) * 2023
    ),
    SPECIAL_SALE("특별 할인",
            (orders, day) -> day.getStar(),
            (orders, day) -> 1000
    ),
    CHRISTMAS_SALE("크리스마스 디데이 할인",
            (orders, day) -> day.getDayOfMonth() <= 25,
            (orders, day) -> 900 + day.getDayOfMonth() * 100
    );

    private final String name;
    private final BiPredicate<Orders, Day> condition;
    private final BiFunction<Orders, Day, Integer> saleFunction;

    SaleEvent(String name, BiPredicate<Orders, Day> condition, BiFunction<Orders, Day, Integer> saleFunction) {
        this.name = name;
        this.condition = condition;
        this.saleFunction = saleFunction;
    }

    public static boolean hasEvent(Event event) {
        try {
            GiftEvent.valueOf(event.getName());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean checkEventTarget(Orders orders, Day day) {
        return condition.test(orders, day);
    }

    @Override
    public Map<Event, Integer> getBenefitInfo(Orders orders, Day day) {
        return Map.of(this, this.saleFunction.apply(orders, day));
    }
}
