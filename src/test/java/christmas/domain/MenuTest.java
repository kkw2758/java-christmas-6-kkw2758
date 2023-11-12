package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @DisplayName("메뉴판에 입력한 이름의 메뉴가 있으면 true를 반환한다.")
    @ParameterizedTest
    //given
    @ValueSource(strings = {"양송이수프", "타파스", "티본스테이크", "아이스크림", "제로콜라"})
    void hasTest(String name) {
        //when & then
        assertThat(Menu.has(name)).isEqualTo(true);
    }

    @DisplayName("메뉴판에 입력한 이름의 메뉴가 없으면 false를 반환한다.")
    @ParameterizedTest
    //given
    @ValueSource(strings = {"제로펩시", "닭가슴살", "단백질쉐이크", "탕후루", "젤리"})
    void notHasTest(String name) {
        //when & then
        assertThat(Menu.has(name)).isEqualTo(false);
    }

    @DisplayName("메뉴판에 입력한 이름의 메뉴가 있으면 메뉴 객체를 반환한다.")
    @Test
    void findMenuByNameSuccessTest() {
        //given
        List<Menu> menus = new ArrayList<>(
                List.of(Menu.ZERO_COLA, Menu.CHOCOLATE_CAKE, Menu.TAPAS, Menu.CHRISTMAS_PASTA));
        List<String> names = new ArrayList<>(List.of("제로콜라", "초코케이크", "타파스", "크리스마스파스타"));

        //when & then
        for (int index = 0; index < menus.size(); index++) {
            assertThat(Menu.findMenuByName(names.get(index))).isEqualTo(menus.get(index));
        }
    }

    @DisplayName("메뉴판에 입력한 이름의 메뉴가 없으면 에러가 발생한다.")
    @ParameterizedTest
    //given
    @ValueSource(strings = {"돼지국밥", "제육볶음", "돈까스"})
    void findMenuByNameExceptionTest(String name) {
        //when & then
        assertThatThrownBy(() -> Menu.findMenuByName(name))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(ErrorMessage.NAME_NOT_IN_MENU_ERROR.getMessage());
    }
}

