package christmas.service;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.DiscountEvent;
import christmas.domain.event.PresentEvent;
import christmas.dto.request.OrdersRequest;
import java.util.Arrays;
import java.util.HashMap;

public class ChristmasPromotionService {
    private static final ChristmasPromotionService instance = new ChristmasPromotionService();

    private ChristmasPromotionService() {
    }

    public void getPromotionResult(Orders orders, VisitDate visitDate) {
        int totalPriceBeforeSale = orders.calculateTotalPriceBeforeSale();
        HashMap<String, Integer> benefitInfo = getBenefitInfo(orders, visitDate);
    }

    private HashMap<String, Integer> getBenefitInfo(Orders orders, VisitDate visitDate) {
        HashMap<String, Integer> benefitInfo = new HashMap<>();
        Arrays.stream(DiscountEvent.values())
                .filter(discountEvent -> discountEvent.isDiscountTarget(orders, visitDate))
                .forEach(discountEvent -> benefitInfo.put(discountEvent.getName(), discountEvent.calculateBenefitPrice(orders,visitDate)));
        Arrays.stream(PresentEvent.values())
                .filter(presentEvent -> presentEvent.isDiscountTarget(orders, visitDate))
                .forEach(presentEvent -> benefitInfo.put(presentEvent.getName(), presentEvent.calculateBenefitPrice()));
        return benefitInfo;
    }

    public static ChristmasPromotionService getInstance() {
        return instance;
    }

    public VisitDate generateVisitDate(int visitDate) {
        return VisitDate.from(visitDate);
    }

    public Orders generateOrders(OrdersRequest ordersRequest) {
        return Orders.from(ordersRequest);
    }
}
