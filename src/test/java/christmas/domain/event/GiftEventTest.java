package christmas.domain.event;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Count;
import christmas.domain.Day;
import christmas.domain.Name;
import christmas.domain.Orders;
import christmas.domain.dto.OrderDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {
    @DisplayName("증정 이벤트 : 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.")
    @Test
    void benefitInfoTest() {
        //given
        Orders eventTargetOrders = Orders.of(List.of(
                        OrderDto.of(Name.from("티본스테이크"), Count.from(1)),
                        OrderDto.of(Name.from("바비큐립"), Count.from(1)),
                        OrderDto.of(Name.from("초코케이크"), Count.from(2)),
                        OrderDto.of(Name.from("제로콜라"), Count.from(1))
                )
        );

        //when & then
        assertThat(GiftEvent.GIFT.getBenefitInfo(eventTargetOrders, Day.of(3))).isEqualTo(Map.of(GiftEvent.GIFT, 25000));
    }

    @DisplayName("증정 이벤트 : 총 주문 금액이 12만원이 안된다면 아무것도 증정하지 않는다.")
    @Test
    void notApplyGiftEventTest() {
        //given
        Orders notEventTargetOrders = Orders.of(List.of(
                        OrderDto.of(Name.from("티본스테이크"), Count.from(1)),
                        OrderDto.of(Name.from("바비큐립"), Count.from(1))
                )
        );

        //when & then
        assertThat(GiftEvent.GIFT.getBenefitInfo(notEventTargetOrders, Day.of(3))).isEqualTo(Map.of());
    }
}
