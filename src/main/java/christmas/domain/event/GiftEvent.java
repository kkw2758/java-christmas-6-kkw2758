package christmas.domain.event;

import christmas.domain.Day;
import christmas.domain.Menu;
import christmas.domain.Orders;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiPredicate;

public enum GiftEvent implements Event {
    GIFT("증정 이벤트",
            (orders, day) -> orders.calculatePriceBeforeSale() >= 120000,
            new EnumMap<Menu, Integer>(Map.of(Menu.CHAMPAGNE, 1))
    );

    private final String name;
    private final BiPredicate<Orders, Day> condition;
    private final Map<Menu, Integer> giftInfo;

    GiftEvent(String name, BiPredicate<Orders, Day> condition, Map<Menu, Integer> giftInfo) {
        this.name = name;
        this.condition = condition;
        this.giftInfo = giftInfo;
    }

    private int calculateGiftPrice() {
        return giftInfo.keySet().stream()
                .map((menu) -> giftInfo.get(menu) * menu.getPrice())
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean checkEventTarget(Orders orders, Day day) {
        return condition.test(orders, day);
    }

    @Override
    public Map<Event, Integer> getBenefitInfo(Orders orders, Day day) {
        if (checkEventTarget(orders, day)) {
            return Map.of(this, calculateGiftPrice());
        }
        return Map.of();
    }

    public Map<Menu, Integer> getGiftMenu(Orders orders, Day day) {
        if (checkEventTarget(orders, day)) {
            return this.giftInfo;
        }
        return Map.of();
    }
}
