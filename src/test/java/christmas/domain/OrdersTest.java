package christmas.domain;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.dto.request.OrderRequest;
import christmas.dto.request.OrdersRequest;
import christmas.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {
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

    @DisplayName("주문 메뉴가 20개를 초과하지 않고 중복된 메뉴가 없으며 음료만으로 구성되어 있지 않다면 정상 실행된다.")
    @Test
    void ordersSuccessTest() {
        // when & then
        assertDoesNotThrow(() -> Orders.from(generateOrdersRequest("티본스테이크-1,레드와인-2,해산물파스타-1")));
    }
    @DisplayName("주문 목록에 중복 메뉴를 포함하는 경우 예외 처리한다.")
    @Test
    void duplicateMenuExceptionTest() {
        // when & then
        assertThatThrownBy(() -> Orders.from(generateOrdersRequest("티본스테이크-1,티본스테이크-1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 목록에 음료만 있을 경우 예외 처리한다.")
    @Test
    void onlyDrinkExceptionTest() {
        // when & then
        assertThatThrownBy(() -> Orders.from(generateOrdersRequest("제로콜라-1,레드와인-1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴가 20개를 초과하면 예외 처리한다.")
    @Test
    void menuCountExceptionTest() {
        // when & then
        assertThatThrownBy(() -> Orders.from(generateOrdersRequest("양송이수프-11,레드와인-10")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
