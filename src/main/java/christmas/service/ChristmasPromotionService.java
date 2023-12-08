package christmas.service;

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

    public VisitDate generateVisitDate(int visitDate) {
        return VisitDate.from(visitDate);
    }

    public Orders generateOrders(OrdersRequest ordersRequest) {
        return Orders.from(ordersRequest);
    }
}
