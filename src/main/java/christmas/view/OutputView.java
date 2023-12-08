package christmas.view;


import christmas.domain.Badge;
import christmas.domain.Menu;
import christmas.dto.response.OrderResponse;
import christmas.dto.response.OrdersResponse;
import christmas.dto.response.PromotionResult;
import java.util.Map;

public class OutputView {
    private static final OutputView instance = new OutputView();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String TOTAL_PRICE_BEFORE_SALE_TAG = "<할인 전 총주문 금액>";
    private static final String TOTAL_PRICE_BEFORE_SALE_FORMAT = "%,d원";
    private static final String BENEFIT_INFO_TAG = "<혜택 내역>";
    private static final String PRESENT_INFO_TAG = "<증정 메뉴>";
    private static final String TOTAL_BENEFIT_PRICE_TAG = "<총혜택 금액>";
    private static final String TOTAL_PRICE_AFTER_SALE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TAG = "<12월 이벤트 배지>";
    private static final String NOTHING = "없음";
    private static final String TOTAL_PRICE_AFTER_SALE_FORMAT = "%,d원";
    private static final String ORDERS_TAG = "<주문 메뉴>";
    private static final String ORDERS_FORMAT = "%s %d개";
    private static final String TOTAL_BENEFIT_PRICE_FORMAT = "-%,d원";
    private static final String PRESENT_INFO_FORMAT = "%s %d개";
    private static final String BENEFIT_INFO_FORMAT = "%s : -%,d원";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public static void printlnMessage(String message) {
        System.out.println(message);
    }

    public static void printlnFormat(String message, Object... args) {
        printlnMessage(String.format(message, args));
    }


    public void printOrders(OrdersResponse ordersResponse) {
        printlnMessage(ORDERS_TAG);
        for (OrderResponse orderResponse : ordersResponse.orderResponses()) {
            printlnFormat(ORDERS_FORMAT, orderResponse.menu(), orderResponse.count());
        }
    }

    public void printStartMessage() {
        printlnMessage(START_MESSAGE);
    }

    public void printPromotionResult(PromotionResult promotionResult) {
        printTotalPriceBeforeSale(promotionResult.totalPriceBeforesale());
        printPresentInfo(promotionResult.presentInfo());
        printBenefitInfo(promotionResult.benefitInfo());
        printTotalBenefitPrice(promotionResult.totalBenefitPrice());
        printTotalPriceAfterSale(promotionResult.totalPriceAfterSale());
        printBadge(promotionResult.badge());
    }

    private void printBadge(Badge badge) {
        printlnMessage(BADGE_TAG);
        printlnMessage(badge.getName());
    }

    private void printTotalPriceAfterSale(int totalPriceAfterSale) {
        printlnMessage(TOTAL_PRICE_AFTER_SALE);
        printlnFormat(TOTAL_PRICE_AFTER_SALE_FORMAT, totalPriceAfterSale);
    }

    private void printTotalBenefitPrice(int totalBenefitPrice) {
        printlnMessage(TOTAL_BENEFIT_PRICE_TAG);
        printlnFormat(TOTAL_BENEFIT_PRICE_FORMAT, totalBenefitPrice);
    }

    private void printPresentInfo(Map<Menu, Integer> presentInfo) {
        printlnMessage(PRESENT_INFO_TAG);
        if (presentInfo.isEmpty()) {
            printlnMessage(NOTHING);
            return;
        }
        presentInfo.forEach((menu, count) -> printlnFormat(PRESENT_INFO_FORMAT, menu.getName(), count));
    }

    private void printBenefitInfo(Map<String, Integer> benefitInfo) {
        printlnMessage(BENEFIT_INFO_TAG);
        if (benefitInfo.isEmpty()) {
            printlnMessage(NOTHING);
            return;
        }
        benefitInfo.forEach((event, benefitPrice) -> printlnFormat(BENEFIT_INFO_FORMAT, event, benefitPrice
        ));
    }

    private void printTotalPriceBeforeSale(int totalPriceBeforesale) {
        printlnMessage(TOTAL_PRICE_BEFORE_SALE_TAG);
        printlnFormat(TOTAL_PRICE_BEFORE_SALE_FORMAT, totalPriceBeforesale);
    }
}
