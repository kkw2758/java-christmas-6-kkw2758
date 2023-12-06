package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("어떤 메뉴가 특정 카테고리에 속하는지 확인한다.")
    @Test
    void isSpecificMenuCategoryTest() {
        // given & then & then
        assertAll(
                () -> assertTrue(Menu.BBQ_LIP.isSpecificMenuCategory(MenuCategory.MAIN)),
                () -> assertFalse(Menu.BBQ_LIP.isSpecificMenuCategory(MenuCategory.DESSERT))
        );
    }

}
