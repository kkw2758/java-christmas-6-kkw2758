package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountTest {
    @Test
    @DisplayName("메뉴 주문 수량이 1미만이면 에러 발생")
    void countOutRangeTest() {
        //given
        int count = 0;

        //when & then
        assertThatThrownBy(() -> Count.from(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
    }

    @DisplayName("메뉴 주문 수량이 1이상이면 정상 실행")
    @ParameterizedTest
    //given
    @ValueSource(ints = {1, 15, 25})
    void countInRangeTest(int count) {
        //when & then
        Assertions.assertDoesNotThrow(() -> Count.from(count));
    }
}
