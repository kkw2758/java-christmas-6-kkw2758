package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @DisplayName("할인 전 총주문 금액을 구한다.")
    @Test
    void calculateTotalPriceBeforeSaleTest() {
        // given
        OrdersRequest ordersRequest = Parser.toOrdersRequest("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
                ErrorMessage.INVALID_ORDERS);
        Orders orders = Orders.from(ordersRequest);

        // when
        int actual = orders.calculateTotalPriceBeforeSale();
        int expected = 142_000;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주문에 포함된 특정 카테고리 메뉴의 수를 구한다.")
    @Test
    void getSpecificCategoryCountTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2,타파스-3,제로콜라-4", ErrorMessage.INVALID_ORDERS));

        // when & then
        assertAll(
                () -> assertThat(orders.getSpecificCategoryCount(Category.MAIN)).isEqualTo(1),
                () -> assertThat(orders.getSpecificCategoryCount(Category.DESSERT)).isEqualTo(2),
                () -> assertThat(orders.getSpecificCategoryCount(Category.APPETIZER)).isEqualTo(3),
                () -> assertThat(orders.getSpecificCategoryCount(Category.DRINK)).isEqualTo(4)
        );
    }
//    hasSpecificCategory

    @DisplayName("주문에 특정 카테고리 메뉴가 포함되어 있는지 확인한다.")
    @Test
    void hasSpecificCategoryTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,타파스-3", ErrorMessage.INVALID_ORDERS));

        // when & then
        assertAll(
                () -> assertTrue(orders.hasSpecificCategory(Category.MAIN)),
                () -> assertTrue(orders.hasSpecificCategory(Category.APPETIZER)),
                () -> assertFalse(orders.hasSpecificCategory(Category.DESSERT)),
                () -> assertFalse(orders.hasSpecificCategory(Category.DRINK))
        );
    }
}