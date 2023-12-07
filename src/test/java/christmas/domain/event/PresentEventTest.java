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

    @DisplayName("총 주문 금액이 120_000원이 넘으면 증정 이벤트 대상이다.")
    @Test
    void isDiscountTarget() {
        //given
        Orders targetOrders = Orders.from(Parser.generateOrdersRequest("티본스테이크-2,초코케이크-3,제로콜라-2"));
        Orders notTargetOrders = Orders.from(Parser.generateOrdersRequest("티본스테이크-2"));

        // when & then
        assertAll(
                () -> assertTrue(PresentEvent.GIFT.isDiscountTarget(targetOrders, VisitDate.from(1))),
                () -> assertFalse(PresentEvent.GIFT.isDiscountTarget(notTargetOrders, VisitDate.from(1))));
    }

    @DisplayName("증정 이벤트의 혜택 금액(샴페인 1개의 금액)을 구한다.")
    @Test
    void calculateBenefitPrice() {
        //given
        Orders targetOrders = Orders.from(Parser.generateOrdersRequest("티본스테이크-2,초코케이크-3,제로콜라-2"));

        // when & then
        assertThat(PresentEvent.GIFT.calculateBenefitPrice()).isEqualTo(25_000);
    }
}
