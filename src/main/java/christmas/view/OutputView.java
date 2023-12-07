package christmas.view;

import christmas.domain.VisitDate;
import christmas.dto.response.OrderResponse;
import christmas.dto.response.OrdersResponse;
import christmas.dto.response.PromotionResult;
import java.util.HashMap;

public class OutputView {
    private static final OutputView instance = new OutputView();

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
        printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventBenefitPreviewMessage(VisitDate visitDate) {
        printlnFormat("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDate.getVisitDate());
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
        printlnMessage("<주문 메뉴>");
        for (OrderResponse orderResponse : ordersResponse.orderResponses()) {
            printOrderResponse(orderResponse);
        }
        printNewLine();
    }

    private void printOrderResponse(OrderResponse orderResponse) {
        printlnFormat("%s %d개", orderResponse.menu(), orderResponse.count());
        printNewLine();
    }

    private void printTotalPriceBeforeSale(int totalPriceBeforeSale) {
        printlnMessage("<할인 전 총주문 금액>");
        printlnFormat("%,d원", totalPriceBeforeSale);
        printNewLine();
    }

    private void printPresentItems(HashMap<String, Integer> presentItems) {
        printlnMessage("<증정 메뉴>");
        if (presentItems.isEmpty()) {
            printlnMessage("없음");
            printNewLine();
            return;
        }
        presentItems.forEach(this::printPresentItem);
        printNewLine();
    }

    private void printPresentItem(String itemName, int count) {
        printlnFormat("%s %d개", itemName, count);
    }

    private void printBenefitInfo(HashMap<String, Integer> benefitInfo) {
        printlnMessage("<혜택 내역>");
        if (benefitInfo.isEmpty()) {
            printlnMessage("없음");
            printNewLine();
            return;
        }
        benefitInfo.forEach((benefitName, benefitPrice) -> printlnFormat("%s: -%,d원", benefitName, benefitPrice));
        printNewLine();
    }

    private void printTotalBenefitPrice(int totalBenefitPrice) {
        printlnMessage("<총혜택 금액>");
        printlnFormat("%,d원", totalBenefitPrice * -1);
        printNewLine();
    }

    private void printTotalPriceAfterSale(int totalPriceAfterSale) {
        printlnMessage("<할인 후 예상 결제 금액>");
        printlnFormat("%,d원", totalPriceAfterSale);
        printNewLine();
    }

    private void printBadge(String badge) {
        printlnMessage("<12월 이벤트 배지>");
        printMessage(badge);
    }
}
