package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @DisplayName("메뉴에 없는 메뉴 이름을 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"아이스티", "피자"})
    void menuExceptionTest(String menu) {
        // when & then
        assertThatThrownBy(() -> Menu.from(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴에 있는 메뉴 이름을 입력하면 정상 동작한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "양송이수프"})
    void menuNoExceptionTest(String menu) {
        // when & then
        assertDoesNotThrow(() -> Menu.from(menu));
    }
}
