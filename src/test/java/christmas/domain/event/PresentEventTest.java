package christmas.domain.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.dto.request.OrderRequest;
import christmas.dto.request.OrdersRequest;
import christmas.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PresentEventTest {
    private static final String COMMA_DELIMITER = ",";
    private static final String DASH_DELIMITER = "-";
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private OrdersRequest generateOrdersRequest(String orders) {
        return new OrdersRequest(Parser.split(orders, COMMA_DELIMITER).stream()
                .map(this::generateOrderRequest)
                .toList());
    }

    private OrderRequest generateOrderRequest(String order) {
        String menu = Parser.split(order, DASH_DELIMITER).get(MENU_INDEX);
        String count = Parser.split(order, DASH_DELIMITER).get(COUNT_INDEX);
        return new OrderRequest(menu, Integer.parseInt(count));
    }

    @DisplayName("총 주문 금액이 120_000원이 넘으면 증정 이벤트 대상이다.")
    @Test
    void isDiscountTarget() {
        //given
        Orders targetOrders = Orders.from(generateOrdersRequest("티본스테이크-2,초코케이크-3,제로콜라-2"));
        Orders notTargetOrders = Orders.from(generateOrdersRequest("티본스테이크-2"));

        // when & then
        assertAll(
                () -> assertTrue(PresentEvent.GIFT.isDiscountTarget(targetOrders, VisitDate.from(1))),
                () -> assertFalse(PresentEvent.GIFT.isDiscountTarget(notTargetOrders, VisitDate.from(1))));
    }

    @DisplayName("증정 이벤트의 혜택 금액(샴페인 1개의 금액)을 구한다.")
    @Test
    void calculateBenefitPrice() {
        //given
        Orders targetOrders = Orders.from(generateOrdersRequest("티본스테이크-2,초코케이크-3,제로콜라-2"));

        // when & then
        assertThat(PresentEvent.GIFT.calculateBenefitPrice()).isEqualTo(25_000);
    }
}
