package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountTest {
    @DisplayName("주문 수량이 1이상 20이하가 아니라면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 21})
    void countExceptionTest(int count) {
        // when & then
        Assertions.assertThatThrownBy(() -> Count.from(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 수량이 1이상 20이하라면 정상 동작한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void countSuccessTest(int count) {
        // when & then
        assertDoesNotThrow(() -> Count.from(count));
    }
}