package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {
    @DisplayName("주문 입력의 형식(주문 메뉴 - 수량)이 일치하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    //given
    @ValueSource(strings = {"티본스테이크-1-2", "1-티본스테이크", "티본스테이크-1.5", "삼각김밥-3", "-"})
    void getOrderCountWithMenuTest(String order) {
        //when & then
        assertThatThrownBy(() -> Order.of(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
    }
}
