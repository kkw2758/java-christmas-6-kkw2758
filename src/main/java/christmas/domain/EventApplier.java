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
    private final Map<Event, Integer> benefitInfo = new EnumMap<>(Event.class);

    private EventApplier(Orders orders, Day day) {
        applyEvent(orders, day);
    }

    public static EventApplier of(Orders orders, Day day) {
        return new EventApplier(orders, day);
    }

    public int calculateTotalBenefitAmount() {
        return calculateTotalSaleAmount() + calculateGiftAmount();
    }

    public int calculateTotalSaleAmount() {
        return benefitInfo.keySet().stream()
                .filter((event) -> event != Event.GIFT)
                .map(benefitInfo::get)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateGiftAmount() {
        return giftMenu.keySet().stream()
                .map((gift) -> gift.getPrice() * giftMenu.get(gift))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculatePriceAfterSale(int priceBeforeSale) {
        return priceBeforeSale - calculateTotalSaleAmount();
    }

    public Map<Menu, Integer> getGiftMenu() {
        return Collections.unmodifiableMap(giftMenu);
    }

    public Map<Event, Integer> getBenefitInfo() {
        return Collections.unmodifiableMap(benefitInfo);
    }

    private void applyEvent(Orders orders, Day day) {
        applyWeekdaySaleEvent(orders, day);
        applyWeekendSaleEvent(orders, day);
        applyChristmasSaleEvent(day);
        applySpecialSaleEvent(day);
        applyGiftEvent(orders);
    }

    private void applyWeekdaySaleEvent(Orders orders, Day day) {
        if (!day.isWeekend() && orders.calculateCategoryCount(Category.DESSERT) != 0) {
            benefitInfo.put(Event.WEEKDAY_SALE,
                    orders.calculateCategoryCount(Category.DESSERT) * WEEKDAY_DISCOUNT_PRICE);
        }
    }

    private void applyWeekendSaleEvent(Orders orders, Day day) {
        if (day.isWeekend() && orders.calculateCategoryCount(Category.MAIN) != 0) {
            benefitInfo.put(Event.WEEKEND_SALE, orders.calculateCategoryCount(Category.MAIN) * WEEKEND_DISCOUNT_PRICE);
        }
    }

    private void applyChristmasSaleEvent(Day day) {
        if (day.getDayOfMonth() <= 25) {
            benefitInfo.put(Event.CHRISTMAS_SALE, 900 + day.getDayOfMonth() * 100);
        }
    }

    private void applySpecialSaleEvent(Day day) {
        if (day.getStar()) {
            benefitInfo.put(Event.SPECIAL_SLAE,SPECIAL_DISCOUNT_PRICE);
        }
    }

    private void applyGiftEvent(Orders orders) {
        if (orders.calculatePriceBeforeSale() >= MIN_PRICE_FOR_GIFT) {
            giftMenu.put(GIFT, GIFT_COUNT);
            benefitInfo.put(Event.GIFT, GIFT.getPrice());
        }
    }
}
