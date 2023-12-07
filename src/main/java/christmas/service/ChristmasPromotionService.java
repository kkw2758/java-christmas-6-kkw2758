package christmas.service;

import christmas.domain.Menu;
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
        HashMap<String, Integer> presentItems = getPresentItems(orders, visitDate);
        int totalBenefitPrice = calculateTotalBenefitPrice(benefitInfo);
        int totalPriceAfterSale = calculateTotalPriceAfterSale(totalPriceBeforeSale, totalBenefitPrice, presentItems);
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

    private HashMap<String, Integer> getPresentItems(Orders orders, VisitDate visitDate) {
        HashMap<String, Integer> presentItems = new HashMap<>();
        Arrays.stream(PresentEvent.values())
                .filter(presentEvent -> presentEvent.isDiscountTarget(orders, visitDate))
                .forEach(presentEvent -> presentItems.putAll(presentEvent.getPresentItems()));
        return presentItems;
    }

    private int calculateTotalBenefitPrice(HashMap<String, Integer> benefitInfo) {
        return benefitInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateTotalPriceAfterSale(int totalPriceBeforeSale, int totalBenefitPrice,
                                             HashMap<String, Integer> presentItems) {
        return totalPriceBeforeSale - totalBenefitPrice + calculatePresentItemsBenefit(presentItems);
    }

    private int calculatePresentItemsBenefit(HashMap<String, Integer> presentItems) {
        return presentItems.entrySet().stream()
                .map(stringIntegerEntry -> Menu.from(stringIntegerEntry.getKey()).getPrice()
                        * stringIntegerEntry.getValue())
                .mapToInt(Integer::intValue)
                .sum();
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
