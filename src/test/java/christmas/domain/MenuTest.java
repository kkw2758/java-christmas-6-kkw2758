package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"삼각김밥", "라면"})
    void menuExceptionTest(String name) {
        // when & then
        assertThatThrownBy(() -> Menu.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴판에 있는 메뉴를 입력하면 예외 정상 동작한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스", "제로콜라"})
    void menuSuccessTest(String name) {
        // when & then
        assertDoesNotThrow(() -> Menu.from(name));
    }
}