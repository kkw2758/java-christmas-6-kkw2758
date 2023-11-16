package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.SaleEvent;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class EventResult {
    private final Map<Menu, Integer> giftMenu = new EnumMap<>(Menu.class);
    private final Map<Event, Integer> benefitInfo = new HashMap<>();

    private EventResult(Orders orders, Day day) {
        applySaleEvent(orders, day);
        applyGiftEvent(orders, day);
    }

    public static EventResult of(Orders orders, Day day) {
        return new EventResult(orders, day);
    }

    public int calculateTotalBenefitAmount() {
        return calculateTotalSaleAmount() + calculateGiftAmount();
    }

    public int calculateTotalSaleAmount() {
        return benefitInfo.keySet().stream()
                .filter(SaleEvent::hasEvent)
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

    private void applySaleEvent(Orders orders, Day day) {
        for (SaleEvent saleEvent : SaleEvent.values()) {
            benefitInfo.putAll(saleEvent.getBenefitInfo(orders, day));
        }
    }

    private void applyGiftEvent(Orders orders, Day day) {
        for (GiftEvent giftEvent : GiftEvent.values()) {
            benefitInfo.putAll(giftEvent.getBenefitInfo(orders, day));
            giftMenu.putAll(giftEvent.getGiftMenu(orders, day));
        }
    }
}
