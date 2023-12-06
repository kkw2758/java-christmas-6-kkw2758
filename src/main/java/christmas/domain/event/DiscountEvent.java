package christmas.domain.event;

import christmas.domain.MenuCategory;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum DiscountEvent {

    WEEK_DAY("평일 할인",
            (orders, visitDate) -> List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY)
                    .contains(visitDate.getDayOfWeek()),
            (orders, visitDate) -> orders.getSpecificMenuCategoryCount(MenuCategory.DESSERT) * 2023
    ),
    WEEKEND("주말 할인",
            (orders, visitDate) -> List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)
                    .contains(visitDate.getDayOfWeek()),
            (orders, visitDate) -> orders.getSpecificMenuCategoryCount(MenuCategory.MAIN) * 2023
    ),
    CHRISTMAS_D_DAY("크리스마스 디데이 할인",
            (orders, visitDate) -> visitDate.getVisitDate() >= 1 && visitDate.getVisitDate() <= 25,
            (orders, visitDate) -> 1000 + 100 * (visitDate.getVisitDate() - 1)
    ),
    SPECIAL("특별 할인",
            (orders, visitDate) -> List.of(3,10,17,24,25,31).contains(visitDate.getVisitDate()),
            (orders, VisitDate) -> 1000
            );

    private final String name;
    private final BiPredicate<Orders, VisitDate> targetCheckFunction;
    private final BiFunction<Orders, VisitDate, Integer> saleFunction;

    DiscountEvent(String name, BiPredicate<Orders, VisitDate> targetCheckFunction,
                  BiFunction<Orders, VisitDate, Integer> saleFunction) {
        this.name = name;
        this.targetCheckFunction = targetCheckFunction;
        this.saleFunction = saleFunction;
    }

    public boolean isDiscountTarget(Orders orders, VisitDate visitDate) {
        return targetCheckFunction.test(orders, visitDate);
    }

    public int calculateBenefitPrice(Orders orders, VisitDate visitDate) {
        return saleFunction.apply(orders, visitDate);
    }
}
