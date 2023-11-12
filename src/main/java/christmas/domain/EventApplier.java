package christmas.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class EventApplier {
    private final int WEEKDAY_DISCOUNT_PRICE = 2023;
    private final int WEEKEND_DISCOUNT_PRICE = 2023;
    private final int SPECIAL_DISCOUNT_PRICE = 1000;
    private final int MIN_PRICE_FOR_GIFT = 120000;
    private final int GIFT_COUNT = 1;
    private final Menu GIFT = Menu.CHAMPAGNE;
    private final Map<Menu, Integer> giftMenu = new EnumMap<>(Menu.class);
    private final Map<Event, Integer> benefitsInfo = new EnumMap<>(Event.class);

    private EventApplier(Orders orders, Day day) {
        applyEvent(orders, day);
    }

    public static EventApplier of(Orders orders, Day day) {
        return new EventApplier(orders, day);
    }

    public int calculateTotalSalePrice() {
        return benefitsInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<Menu, Integer> getGiftMenu() {
        return Collections.unmodifiableMap(giftMenu);
    }

    public Map<Event, Integer> getBenefitsInfo() {
        return Collections.unmodifiableMap(benefitsInfo);
    }

    private void applyEvent(Orders orders, Day day) {
        applyWeekdaySaleEvent(orders, day);
        applyWeekendSaleEvent(orders, day);
        applyChristmasSaleEvent(day);
        applySpecialSaleEvent(day);
        applyGiftEvent(orders);
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

    private void applyChristmasSaleEvent(Day day) {
        if (day.getDayOfMonth() <= 25) {
            benefitsInfo.put(Event.CHRISTMAS_SALE, 900 + day.getDayOfMonth() * 100);
        }
    }

    private void applySpecialSaleEvent(Day day) {
        if (day.getStar()) {
            benefitsInfo.put(Event.SPECIAL_SLAE,SPECIAL_DISCOUNT_PRICE);
        }
    }

    private void applyGiftEvent(Orders orders) {
        if (orders.calculatePriceBeforeSale() >= MIN_PRICE_FOR_GIFT) {
            giftMenu.put(GIFT, GIFT_COUNT);
            benefitsInfo.put(Event.GIFT, GIFT.getPrice());
        }
    }
}
