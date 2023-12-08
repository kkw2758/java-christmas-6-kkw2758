package christmas.domain;

import christmas.dto.request.OrdersRequest;
import christmas.exception.ErrorMessage;
import christmas.utils.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {
    @DisplayName("주문에 중복된 메뉴가 있으면 예외 처리한다.")
    @Test
    void duplicateMenuExceptionTest() {
        // given
        OrdersRequest ordersRequest = Parser.toOrdersRequest("티본스테이크-1,제로콜라-1,티본스테이크-2", ErrorMessage.INVALID_ORDERS);

        // when & then
        Assertions.assertThatThrownBy(() -> Orders.from(ordersRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("한 번에 최대 20개보다 많은 메뉴를 주문하면 예외 처리한다.")
    @Test
    void maximumOrdersException() {
        // given
        OrdersRequest ordersRequest = Parser.toOrdersRequest("티본스테이크-11,제로콜라-11", ErrorMessage.INVALID_ORDERS);

        // when & then
        Assertions.assertThatThrownBy(() -> Orders.from(ordersRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문에 음료만 있다면 예외 처리한다.")
    @Test
    void ordersHasOnlyDrinkException() {
        // given
        OrdersRequest ordersRequest = Parser.toOrdersRequest("제로콜라-1,레드와인-1", ErrorMessage.INVALID_ORDERS);

        // when & then
        Assertions.assertThatThrownBy(() -> Orders.from(ordersRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}