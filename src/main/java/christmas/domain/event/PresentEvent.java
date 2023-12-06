package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.util.Map;
import java.util.function.BiPredicate;

public enum PresentEvent {
    GIFT("증정 이벤트",
            (orders, visitDate) -> orders.calculateTotalPriceBeforeSale() >= 120_000,
            Map.of(Menu.CHAMPAGNE, 1)
    );

    private final String name;
    private final BiPredicate<Orders, VisitDate> targetCheckFunction;
    private final Map<Menu, Integer> presentItems;

    PresentEvent(String name, BiPredicate<Orders, VisitDate> targetCheckFunction,
                 Map<Menu, Integer> giftItems) {
        this.name = name;
        this.targetCheckFunction = targetCheckFunction;
        this.presentItems = giftItems;
    }

    public boolean isDiscountTarget(Orders orders, VisitDate visitDate) {
        return targetCheckFunction.test(orders, visitDate);
    }

    public int calculateBenefitPrice() {
        return presentItems.entrySet().stream()
                .map(menuIntegerEntry -> menuIntegerEntry.getKey().getPrice() * menuIntegerEntry.getValue())
                .mapToInt(Integer::intValue)
                .sum();
    }

    public String getName() {
        return name;
    }

    public Map<Menu, Integer> getPresentItems() {
        return presentItems;
    }
}
