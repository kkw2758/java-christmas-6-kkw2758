package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.dto.request.OrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("주문에 대한 가격을 계산한다.")
    @Test
    void calculateOrderPriceTest() {
        // given
        Order order = Order.from(new OrderRequest("티본스테이크", 3));

        // when
        int actual = order.calculateOrderPrice();
        int expected = 55_000 * 3;
        
        // then
        assertThat(actual).isEqualTo(expected);
    }
}