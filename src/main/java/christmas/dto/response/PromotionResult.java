package christmas.dto.response;

import christmas.domain.Menu;
import java.util.Map;

public record PromotionResult(
        int totalPriceBeforesale,
        Map<Menu, Integer> presentInfo,
        Map<String, Integer> benefitInfo,
        int totalBenefitPrice,
        int totalPriceAfterSale,
        christmas.domain.Badge badge
) {
}
