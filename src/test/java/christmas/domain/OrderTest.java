package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.dto.request.OrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("메뉴-수량으로 이루어진 주문의 가격을 계산한다.")
    @Test
    void calculatePriceTest() {
        // given
        OrderRequest orderRequest = new OrderRequest("티본스테이크", 3);
        Order order = Order.from(orderRequest);

        // when
        int actual = order.calculatePrice();
        int expected = 55500 * 3;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
