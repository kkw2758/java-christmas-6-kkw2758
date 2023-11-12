package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.Orders;
import java.util.Map;

public class OutputView {
    private static final String NEWLINE = "\n";
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String DAY_REQUEST_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_REQUEST_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String START_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERS_FORMAT = "%s %d개";
    private static final String BENEFITS_FORMAT = "%s: -%,d원";
    private static final String ORDERS_TAG = "<주문 메뉴>";
    private static final String GIFT_MENU_TAG = "<증정 메뉴>";
    private static final String BENEFITS_INFO_TAG = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_TAG = "<총혜택 금액>";
    private static final String EVENT_BADGE_TAG = "<12월 이벤트 배지>";
    private static final String TOTAL_BENEFIT_AMOUNT_FORMAT = "-%,d원";
    private static final String PRICE_AFTER_SALE = "<할인 후 예상 결제 금액>";
    private static final String PRICE_AFTER_SALE_FORMAT = "%,d원";

    private static final String TOTAL_PRICE_BEFORE_SALE_TAG = "<할인 전 총주문 금액>";
    private static final String TOTAL_PRICE_BEFORE_SALE = "%,d원";

    public static void println(String message) {
        System.out.println(message);
    }

    public static void printfWithNewLine(String message, Object... args) {
        System.out.printf(message, args);
        System.out.print(NEWLINE);
    }

    public static void printStartMessage() {
        println(START_MESSAGE);
    }

    public static void printDayRequestMessage() {
        println(DAY_REQUEST_MESSAGE);
    }

    public static void printOrderRequestMessage() {
        println(ORDER_REQUEST_MESSAGE);
    }

    public static void printStartPreviewMessage(int day) {
        printfWithNewLine(START_PREVIEW_MESSAGE, day);
    }

    public static void printOrders(Orders orders) {
        println(ORDERS_TAG);
        for (Menu orderedMenu : orders.getOrderedMenus()) {
            printfWithNewLine(ORDERS_FORMAT, orderedMenu.getName(), orders.getOrderCountWithMenu(orderedMenu));
        }
    }

    public static void printTotalPriceBeforeSale(Orders orders) {
        println(TOTAL_PRICE_BEFORE_SALE_TAG);
        printfWithNewLine(TOTAL_PRICE_BEFORE_SALE, orders.calculatePriceBeforeSale());
    }

    public static void printGiftMenu(Map<Menu, Integer> giftMenu) {
        println(GIFT_MENU_TAG);
        if (giftMenu.size() == 0) {
            println("없음");
            return;
        }
        for (Menu menu : giftMenu.keySet()) {
            printfWithNewLine(ORDERS_FORMAT, menu.getName(), giftMenu.get(menu));
        }
    }

    public static void printBenefitsInfo(Map<Event, Integer> benefitsInfo) {
        println(BENEFITS_INFO_TAG);
        if (benefitsInfo.size() == 0) {
            println("없음");
            return;
        }
        for (Event event : benefitsInfo.keySet()) {
            printfWithNewLine(BENEFITS_FORMAT, event.getName(), benefitsInfo.get(event));
        }
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        println("<총혜택 금액>");
        printfWithNewLine(TOTAL_BENEFIT_AMOUNT_FORMAT, totalBenefitAmount);
    }

    public static void printEventBadge(int totalBenefitAmount) {
        println(EVENT_BADGE_TAG);
        println(Badge.findBadge(totalBenefitAmount).getName());
    }

    public static void printPriceAfterSale(int priceAfterSale) {
        println(PRICE_AFTER_SALE);
        printfWithNewLine(PRICE_AFTER_SALE_FORMAT, priceAfterSale);
    }
}
