package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    @DisplayName("메뉴판에 없는 메뉴를 입력하는 경우 에러 발생")
    @ParameterizedTest
    //given
    @ValueSource(strings = {"치킨", "피자"})
    void not_in_menu_test(String name) {
        //when & then
        assertThatThrownBy(() -> Name.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
    }

    @DisplayName("메뉴판에 있는 메뉴를 입력하는 경우 정상 실행")
    @ParameterizedTest
    //given
    @ValueSource(strings = {"양송이수프", "아이스크림", "제로콜라"})
    void in_menu_test(String name) {
        //when & then
        Assertions.assertDoesNotThrow(() -> Name.from(name));
    }
}
