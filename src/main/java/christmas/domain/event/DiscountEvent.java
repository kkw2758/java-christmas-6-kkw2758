package christmas.domain.event;

import christmas.domain.Category;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum DiscountEvent {
    WEEKDAY("평일 할인", (visitDate, orders) -> visitDate.isWeekday() && orders.hasSpecificCategory(Category.DESSERT),
            (visitDate, orders) -> 2023 * orders.getSpecificCategoryCount(Category.DESSERT)),
    WEEKEND("주말 할인", (visitDate, orders) -> visitDate.isWeekend() && orders.hasSpecificCategory(Category.MAIN),
            (visitDate, orders) -> 2023 * orders.getSpecificCategoryCount(Category.MAIN)),
    SPECIAL("특별 할인", (visitDate, orders) -> visitDate.isStarDay(),
            (visitDate, orders) -> 1000),
    CHRISTMAS("크리스마스 디데이 할인",
            (visitDate, orders) -> visitDate.isChristmasEventTarget(),
            (visitDate, orders) -> 1000 + 100 * (visitDate.getValue() - 1));

    private final String name;
    private final BiPredicate<VisitDate, Orders> eventTargetCheckFunction;
    private final BiFunction<VisitDate, Orders, Integer> benefitFunction;

    DiscountEvent(String name, BiPredicate<VisitDate, Orders> eventTargetCheckFunction,
                  BiFunction<VisitDate, Orders, Integer> benefitFunction) {
        this.name = name;
        this.eventTargetCheckFunction = eventTargetCheckFunction;
        this.benefitFunction = benefitFunction;
    }

    public boolean isEventTarget(VisitDate visitDate, Orders orders) {
        return eventTargetCheckFunction.test(visitDate, orders);
    }

    public int calculateBenefitPrice(VisitDate visitDate, Orders orders) {
        return benefitFunction.apply(visitDate, orders);
    }
}
