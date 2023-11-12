package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

public class EventApplier {
    private final int WEEKDAY_DISCOUNT_PRICE = 2023;
    private final int WEEKEND_DISCOUNT_PRICE = 2023;
    private final int SPECIAL_DISCOUNT_PRICE = 1000;
    private final Map<Event, Integer> benefitsInfo = new EnumMap<>(Event.class);

    private EventApplier(Orders orders, Day day) {
        applyEvent(orders, day);
    }

    public static EventApplier of(Orders orders, Day day) {
        return new EventApplier(orders, day);
    }

    private void applyEvent(Orders orders, Day day) {
        applyWeekdaySaleEvent(orders, day);
        applyWeekendSaleEvent(orders, day);
        applySpecialSaleEvent(day);
    }

    private void applyWeekdaySaleEvent(Orders orders, Day day) {
        if (!day.isWeekend()) {
            benefitsInfo.put(Event.WEEKDAY_SALE,
                    orders.calculateCategoryCount(Category.DESSERT) * WEEKDAY_DISCOUNT_PRICE);
        }
    }

    private void applyWeekendSaleEvent(Orders orders, Day day) {
        if (day.isWeekend()) {
            benefitsInfo.put(Event.WEEKEND_SALE, orders.calculateCategoryCount(Category.MAIN) * WEEKEND_DISCOUNT_PRICE);
        }
    }

    private void applySpecialSaleEvent(Day day) {
        if (day.getStar()) {
            benefitsInfo.put(Event.SPECIAL_SLAE,SPECIAL_DISCOUNT_PRICE);
        }
    }
}
