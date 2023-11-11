package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.dto.OrderDto;
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
        List<OrderDto> orders = List.of(
                OrderDto.of(Name.from("티본스테이크"), Count.from(10)),
                OrderDto.of(Name.from("아이스크림"), Count.from(12)));

        //when & then
        assertThatThrownBy(() -> Orders.of(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(ErrorMessage.MAX_MENU_COUNT_ERROR.getMessage(), 20));
    }

    @DisplayName("한번의 주문에서 중복된 품목 주문이 있으면 에러 발생")
    @Test
    void validateDuplicateMenuErrorTest() {
        //given
        List<OrderDto> orders = List.of(
                OrderDto.of(Name.from("티본스테이크"), Count.from(10)),
                OrderDto.of(Name.from("티본스테이크"), Count.from(1)));

        //when & then
        assertThatThrownBy(() -> Orders.of(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
    }

    @DisplayName("한번의 주문에서 모든 메뉴가 음료이면 에러 발생")
    @Test
    void validateOnlyDrinkErrorTest() {
        //given
        List<OrderDto> orders = List.of(
                OrderDto.of(Name.from("제로콜라"), Count.from(10)),
                OrderDto.of(Name.from("레드와인"), Count.from(1)),
                OrderDto.of(Name.from("샴페인"), Count.from(8)));

        //when & then
        assertThatThrownBy(() -> Orders.of(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(ErrorMessage.ONLY_DRINK_ORDER_ERROR.getMessage()));
    }

    @DisplayName("한번의 주문에서 메뉴가 20개 이하고 음료만 시키지 않았으며 중복된 품목 주문이 없으면 정상 실행")
    @Test
    void successOrdersTest() {
        //given
        List<OrderDto> orders = List.of(
                OrderDto.of(Name.from("티본스테이크"), Count.from(10)),
                OrderDto.of(Name.from("아이스크림"), Count.from(10)));

        //when & then
        Assertions.assertDoesNotThrow(() -> Orders.of(orders));
    }

    @DisplayName("할인 전 총 주문 금액을 구한다.")
    @Test
    void calculatePriceBeforeSaleTest() {
        //given
        Orders orders = Orders.of(List.of(
                OrderDto.of(Name.from("티본스테이크"), Count.from(2)),
                OrderDto.of(Name.from("아이스크림"), Count.from(2)),
                OrderDto.of(Name.from("샴페인"), Count.from(1))
        ));
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
        Orders orders = Orders.of(List.of(
                OrderDto.of(Name.from("티본스테이크"), Count.from(3)),
                OrderDto.of(Name.from("아이스크림"), Count.from(2)),
                OrderDto.of(Name.from("초코케이크"), Count.from(1))
        ));

        //when & then
        assertThat(orders.calculateCategoryCount(Category.APPETIZER)).isEqualTo(0);
        assertThat(orders.calculateCategoryCount(Category.MAIN)).isEqualTo(1);
        assertThat(orders.calculateCategoryCount(Category.DESSERT)).isEqualTo(2);
        assertThat(orders.calculateCategoryCount(Category.DRINK)).isEqualTo(0);
    }

}
