package christmas.view;

import christmas.domain.VisitDate;
import christmas.dto.response.OrderResponse;
import christmas.dto.response.OrdersResponse;
import christmas.dto.response.PromotionResult;
import java.util.HashMap;

public class OutputView {
    private static final OutputView instance = new OutputView();
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_BENEFIT_PREVIEW_MESSAGE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String NOTHING = "없음";
    private static final String ORDERS_TAG = "<주문 메뉴>";
    private static final String ORDER_FORMAT = "%s %d개";
    private static final String TOTAL_PRICE_BEFORE_SALE_TAG = "<할인 전 총주문 금액>";
    private static final String PRESENT_ITEMS_TAG = "<증정 메뉴>";
    private static final String PRESENT_ITEM_FORMAT = "%s %d개";
    private static final String BENEFIT_INFO_TAG = "<혜택 내역>";
    private static final String BENEFIT_INFO_FORMAT = "%s: -%,d원";
    private static final String PRICE_FORMAT = "%,d원";
    private static final String TOTAL_BENEFIT_PRICE_TAG = "<총혜택 금액>";
    private static final String TOTAL_PRICE_AFTER_SALE_TAG = "<할인 후 예상 결제 금액>";
    private static final String BADGE_TAG = "<12월 이벤트 배지>";


    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }


    public void printlnMessage(String message) {
        System.out.println(message);
    }

    public void printlnFormat(String message, Object... args) {
        printlnMessage(String.format(message, args));
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void printNewLine() {
        printMessage("\n");
    }

    public void printStartMessage() {
        printlnMessage(START_MESSAGE);
    }

    public void printEventBenefitPreviewMessage(VisitDate visitDate) {
        printlnFormat(EVENT_BENEFIT_PREVIEW_MESSAGE_FORMAT, visitDate.getVisitDate());
        printlnMessage("");
    }

    public void printPromotionResult(PromotionResult promotionResult) {
        printOrdersResponse(promotionResult.ordersResponse());
        printTotalPriceBeforeSale(promotionResult.totalPriceBeforeSale());
        printPresentItems(promotionResult.presentItems());
        printBenefitInfo(promotionResult.benefitInfo());
        printTotalBenefitPrice(promotionResult.totalBenefitPrice());
        printTotalPriceAfterSale(promotionResult.totalPriceAfterSale());
        printBadge(promotionResult.badgeName());
    }

    private void printOrdersResponse(OrdersResponse ordersResponse) {
        printlnMessage(ORDERS_TAG);
        for (OrderResponse orderResponse : ordersResponse.orderResponses()) {
            printOrderResponse(orderResponse);
        }
        printNewLine();
    }

    private void printOrderResponse(OrderResponse orderResponse) {
        printlnFormat(ORDER_FORMAT, orderResponse.menu(), orderResponse.count());
        printNewLine();
    }

    private void printTotalPriceBeforeSale(int totalPriceBeforeSale) {
        printlnMessage(TOTAL_PRICE_BEFORE_SALE_TAG);
        printlnFormat(PRICE_FORMAT, totalPriceBeforeSale);
        printNewLine();
    }

    private void printPresentItems(HashMap<String, Integer> presentItems) {
        printlnMessage(PRESENT_ITEMS_TAG);
        if (presentItems.isEmpty()) {
            printlnMessage(NOTHING);
            printNewLine();
            return;
        }
        presentItems.forEach(this::printPresentItem);
        printNewLine();
    }

    private void printPresentItem(String itemName, int count) {
        printlnFormat(PRESENT_ITEM_FORMAT, itemName, count);
    }

    private void printBenefitInfo(HashMap<String, Integer> benefitInfo) {
        printlnMessage(BENEFIT_INFO_TAG);
        if (benefitInfo.isEmpty()) {
            printlnMessage(NOTHING);
            printNewLine();
            return;
        }
        benefitInfo.forEach((benefitName, benefitPrice) -> printlnFormat(BENEFIT_INFO_FORMAT, benefitName, benefitPrice));
        printNewLine();
    }

    private void printTotalBenefitPrice(int totalBenefitPrice) {
        printlnMessage(TOTAL_BENEFIT_PRICE_TAG);
        printlnFormat(PRICE_FORMAT, totalBenefitPrice * -1);
        printNewLine();
    }

    private void printTotalPriceAfterSale(int totalPriceAfterSale) {
        printlnMessage(TOTAL_PRICE_AFTER_SALE_TAG);
        printlnFormat(PRICE_FORMAT, totalPriceAfterSale);
        printNewLine();
    }

    private void printBadge(String badge) {
        printlnMessage(BADGE_TAG);
        printMessage(badge);
    }
}
