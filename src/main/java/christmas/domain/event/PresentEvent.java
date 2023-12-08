package christmas.domain.event;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.util.Map;
import java.util.function.BiPredicate;

public enum PresentEvent {
    GIFT("증정 이벤트", (visitDate, orders) -> orders.calculateTotalPriceBeforeSale() >= 120_000,
            Map.of(Menu.CHAMPAGNE, 1)
    );

    private final String name;
    private final BiPredicate<VisitDate, Orders> eventTargetCheckFunction;
    private final Map<Menu, Integer> presentInfo;

    PresentEvent(String name, BiPredicate<VisitDate, Orders> eventTargetCheckFunction,
                 Map<Menu, Integer> presentInfo) {
        this.name = name;
        this.eventTargetCheckFunction = eventTargetCheckFunction;
        this.presentInfo = presentInfo;
    }

    public boolean isEventTarget(VisitDate visitDate, Orders orders) {
        return eventTargetCheckFunction.test(visitDate, orders);
    }

    public int calculateBenefitPrice() {
        return presentInfo.entrySet().stream()
                .mapToInt(menuCountEntry -> menuCountEntry.getKey().getPrice() * menuCountEntry.getValue())
                .sum();
    }

    public String getName() {
        return name;
    }

    public Map<Menu, Integer> getPresentInfo() {
        return presentInfo;
    }
}
