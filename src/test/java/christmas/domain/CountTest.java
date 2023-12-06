package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountTest {

    @DisplayName("메뉴 수량이 0이거나 양수가 아니라면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void countRangeExceptionTest(int count) {
        // when & then
        assertThatThrownBy(() -> Count.from(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴 수량이 1이상 20 이하라면 정상 동작한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void countRangeNoExceptionTest(int count) {
        // when & then
        Assertions.assertDoesNotThrow(( ) -> Count.from(count));
    }
}
