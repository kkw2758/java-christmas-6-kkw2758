package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.DiscountEvent;
import christmas.domain.event.PresentEvent;
import christmas.dto.request.OrdersRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChristmasPromotionService {
    private static final ChristmasPromotionService instance = new ChristmasPromotionService();

    private ChristmasPromotionService() {
    }

    public static ChristmasPromotionService getInstance() {
        return instance;
    }

    private int calculateTotalPriceBeforeSale(Orders orders) {
        return orders.calculateTotalPriceBeforeSale();
    }

    private Map<String, Integer> getBenefitInfo(Orders orders, VisitDate visitDate) {
        Map<String, Integer> benefitInfo = new HashMap<>();
        Arrays.stream(DiscountEvent.values())
                .filter(discountEvent -> discountEvent.isEventTarget(visitDate, orders))
                .forEach(discountEvent -> benefitInfo.put(discountEvent.getName(),
                        discountEvent.calculateBenefitPrice(visitDate, orders)));
        Arrays.stream(PresentEvent.values())
                .filter(presentEvent -> presentEvent.isEventTarget(visitDate, orders))
                .forEach(presentEvent -> benefitInfo.put(presentEvent.getName(),
                        presentEvent.calculateBenefitPrice()));
        return benefitInfo;
    }

    private int calculateTotalBenefitPrice(Map<String, Integer> benefitInfo) {
        return benefitInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Map<Menu, Integer> getPresentInfo(Orders orders, VisitDate visitDate) {
        Map<Menu, Integer> presentInfo = new HashMap<>();
        Arrays.stream(PresentEvent.values())
                .filter(presentEvent -> presentEvent.isEventTarget(visitDate, orders))
                .forEach(presentEvent -> presentInfo.putAll(presentEvent.getPresentInfo()));
        return presentInfo;
    }

    private int calculateTotalPresentPrice(Map<Menu, Integer> presentInfo) {
        return presentInfo.entrySet().stream()
                .mapToInt(menuIntegerEntry -> menuIntegerEntry.getKey().getPrice() * menuIntegerEntry.getValue())
                .sum();
    }

    private int calculateTotalPriceAfterSale(Orders orders, Map<String, Integer> benefitInfo,
                                             Map<Menu, Integer> presentInfo) {
        return orders.calculateTotalPriceBeforeSale() - calculateTotalBenefitPrice(benefitInfo)
                + calculateTotalPresentPrice(
                presentInfo);
    }

    public VisitDate generateVisitDate(int visitDate) {
        return VisitDate.from(visitDate);
    }

    public Orders generateOrders(OrdersRequest ordersRequest) {
        return Orders.from(ordersRequest);
    }
}
