package christmas.domain.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.exception.ErrorMessage;
import christmas.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PresentEventTest {
    @DisplayName("총 주문 금액이 120_000이상이면 증정 이벤트를 적용한다.")
    @Test
    void isGiftEventTargetTest() {
        // given
        Orders targetOrders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-2,아이스크림-2,타파스-3,제로콜라-4", ErrorMessage.INVALID_ORDERS));
        Orders noTargetOrders = Orders.from(
                Parser.toOrdersRequest("아이스크림-2,타파스-3,제로콜라-4", ErrorMessage.INVALID_ORDERS));

        // when & then
        assertAll(
                () -> assertTrue(PresentEvent.GIFT.isEventTarget(VisitDate.from(1), targetOrders)),
                () -> assertFalse(PresentEvent.GIFT.isEventTarget(VisitDate.from(1), noTargetOrders))
        );
    }

    @DisplayName("총 주문 금액이 120_000이상이면 증정 이벤트를 적용한다.")
    @Test
    void calculateGiftBenefitPrice() {
        // given
        Orders targetOrders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-2,아이스크림-2,타파스-3,제로콜라-4", ErrorMessage.INVALID_ORDERS));

        // when & then
        assertThat(PresentEvent.GIFT.calculateBenefitPrice()).isEqualTo(25000);
    }
}