package christmas.dto.response;

import java.util.HashMap;

public record PromotionResult(
        OrdersResponse ordersResponse,
        HashMap<String, Integer> benefitInfo,
        HashMap<String, Integer> presentItems,
        int totalPriceBeforeSale,
        int totalBenefitPrice,
        int totalPriceAfterSale,
        String badgeName
) {
}
