package christmas.domain.event;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Day;
import christmas.domain.Orders;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {
    @DisplayName("증정 이벤트 : 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.")
    @Test
    void benefitInfoTest() {
        //given
        Orders eventTargetOrders = Orders.of("티본스테이크-1, 바비큐립-1, 초코케이크-2, 제로콜라-1");

        //when & then
        assertThat(GiftEvent.GIFT.getBenefitInfo(eventTargetOrders, Day.of(3))).isEqualTo(
                Map.of(GiftEvent.GIFT, 25000));
    }

    @DisplayName("증정 이벤트 : 총 주문 금액이 12만원이 안된다면 아무것도 증정하지 않는다.")
    @Test
    void notApplyGiftEventTest() {
        //given
        Orders notEventTargetOrders = Orders.of("티본스테이크-1, 바비큐립-1");

        //when & then
        assertThat(GiftEvent.GIFT.getBenefitInfo(notEventTargetOrders, Day.of(3))).isEqualTo(Map.of());
    }
}
