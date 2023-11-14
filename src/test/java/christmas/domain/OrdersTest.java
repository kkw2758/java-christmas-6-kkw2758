package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.exception.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {
    @DisplayName("한번의 주문에서 메뉴가 20개를 초과하면 에러 발생")
    @Test
    void validateMenuCountErrorTest() {
        //given
        String orders = "티본스테이크-10, 아이스크림-12";

        //when & then
        assertThatThrownBy(() -> Orders.of(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(ErrorMessage.MAX_MENU_COUNT_ERROR.getMessage(), 20));
    }

    @DisplayName("한번의 주문에서 중복된 품목 주문이 있으면 에러 발생")
    @Test
    void validateDuplicateMenuErrorTest() {
        //given
        String orders = "티본스테이크-10, 티본스테이크-1";

        //when & then
        assertThatThrownBy(() -> Orders.of(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
    }

    @DisplayName("한번의 주문에서 모든 메뉴가 음료이면 에러 발생")
    @Test
    void validateOnlyDrinkErrorTest() {
        //given
        String orders = "제로콜라-10, 레드와인-1, 샴페인-8";

        //when & then
        assertThatThrownBy(() -> Orders.of(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(ErrorMessage.ONLY_DRINK_ORDER_ERROR.getMessage()));
    }

    @DisplayName("한번의 주문에서 메뉴가 20개 이하고 음료만 시키지 않았으며 중복된 품목 주문이 없으면 정상 실행")
    @Test
    void successOrdersTest() {
        //given
        String orders = "티본스테이크-10, 아이스크림-10";

        //when & then
        Assertions.assertDoesNotThrow(() -> Orders.of(orders));
    }

    @DisplayName("할인 전 총 주문 금액을 구한다.")
    @Test
    void calculatePriceBeforeSaleTest() {
        //given
        Orders orders = Orders.of("티본스테이크-2, 아이스크림-2, 샴페인-1");

        //when
        int result = orders.calculatePriceBeforeSale();
        int expectedResult = 110000 + 10000 + 25000;

        //when & then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("어떤 카테고리의 음식을 몇개 주문했는지 확인한다.")
    @Test
    void calculateCategoryCountTest() {
        //given
        Orders orders = Orders.of("티본스테이크-3, 아이스크림-2, 초코케이크-1");

        //when & then
        assertThat(orders.calculateCategoryCount(Category.APPETIZER)).isEqualTo(0);
        assertThat(orders.calculateCategoryCount(Category.MAIN)).isEqualTo(3);
        assertThat(orders.calculateCategoryCount(Category.DESSERT)).isEqualTo(3);
        assertThat(orders.calculateCategoryCount(Category.DRINK)).isEqualTo(0);
    }

    @DisplayName("주문한 메뉴들의 목록을 확인한다.")
    @Test
    void getOrderedMenusTest() {
        //given
        Orders orders = Orders.of("티본스테이크-3, 아이스크림-2, 초코케이크-1");
        List<Menu> expectedResult = List.of(Menu.T_BONE_STEAK, Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM);

        //when
        List<Menu> result = orders.getOrderedMenus();

        //then
        assertThat(result).isEqualTo(expectedResult);
    }

    @DisplayName("어떤 음식의 주문이 몇개 들어왔는지 확인한다.")
    @Test
    void getOrderCountWithMenuTest() {
        //given
        Orders orders = Orders.of("티본스테이크-3, 아이스크림-2, 초코케이크-1");

        //when & then
        assertThat(orders.getOrderCountWithMenu(Menu.T_BONE_STEAK)).isEqualTo(3);
        assertThat(orders.getOrderCountWithMenu(Menu.ICE_CREAM)).isEqualTo(2);
        assertThat(orders.getOrderCountWithMenu(Menu.CHOCOLATE_CAKE)).isEqualTo(1);
    }
}
