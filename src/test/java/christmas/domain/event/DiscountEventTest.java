package christmas.domain.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.MenuCategory;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.dto.request.OrderRequest;
import christmas.dto.request.OrdersRequest;
import christmas.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountEventTest {

    private static final String COMMA_DELIMITER = ",";
    private static final String DASH_DELIMITER = "-";
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private OrdersRequest generateOrdersRequest(String orders) {
        return new OrdersRequest(Parser.split(orders, COMMA_DELIMITER).stream()
                .map(this::generateOrderRequest)
                .toList());
    }

    private OrderRequest generateOrderRequest(String order) {
        String menu = Parser.split(order, DASH_DELIMITER).get(MENU_INDEX);
        String count = Parser.split(order, DASH_DELIMITER).get(COUNT_INDEX);
        return new OrderRequest(menu, Integer.parseInt(count));
    }

    private final Orders orders = Orders.from(generateOrdersRequest("티본스테이크-2,초코케이크-3,제로콜라-2"));


    @DisplayName("2023/12/01 ~ 2023/12/25 기간동안 크리스마스 디데이 할인을 적용한다.")
    @Test
    void isChristmasDDayEventTarget() {
        // given & when & then
        assertAll(
                () -> assertTrue(DiscountEvent.CHRISTMAS_D_DAY.isDiscountTarget(orders, VisitDate.from(25))),
                () -> assertTrue(DiscountEvent.CHRISTMAS_D_DAY.isDiscountTarget(orders, VisitDate.from(1))),
                () -> assertFalse(DiscountEvent.CHRISTMAS_D_DAY.isDiscountTarget(orders, VisitDate.from(30)))
        );
    }

    @DisplayName("크리스마스 디데이 할인 혜택 금액을 구한다.")
    @ParameterizedTest
    @CsvSource(value = {"25, 3400", "1, 1000"}, delimiter = ',')
    void calculateChristmasDDayEventBenefitPrice(int visitDate, int expected) {
        // given & when
        int actual = DiscountEvent.CHRISTMAS_D_DAY.calculateBenefitPrice(orders, VisitDate.from(visitDate));

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("일요일 ~ 목요일에는 평일 할인을 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {3,4,5,6,7})
    void isWeekDayEventTarget(int visitDate) {
        // when & then
        assertTrue(DiscountEvent.WEEK_DAY.isDiscountTarget(orders, VisitDate.from(visitDate)));
    }

    @DisplayName("평일 할인 혜택 금액을 구한다.")
    @Test
    void calculateWeekDayBenefitPrice() {
        // given & when
        int actual = DiscountEvent.WEEK_DAY.calculateBenefitPrice(orders, VisitDate.from(7));
        int expected = 2023 * 3;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("금요일 ~ 토요일에는 주말 할인을 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {8,9})
    void isWeekendEventTarget(int visitDate) {
        // when & then
        assertTrue(DiscountEvent.WEEKEND.isDiscountTarget(orders, VisitDate.from(visitDate)));
    }

    @DisplayName("주말 할인 혜택 금액을 구한다.")
    @Test
    void calculateWeekendBenefitPrice() {
        // given & when
        int actual = DiscountEvent.WEEKEND.calculateBenefitPrice(orders, VisitDate.from(8));
        int expected = 2023 * 2;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("달력에 별이 있으면 특별 할인을 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {3,10,17,24,25,31})
    void isSpecialEventTarget(int visitDate) {
        // when & then
        assertTrue(DiscountEvent.SPECIAL.isDiscountTarget(orders, VisitDate.from(visitDate)));
    }

    @DisplayName("특별 할인 혜택 금액을 구한다.")
    @Test
    void calculateSpecialBenefitPrice() {
        // given & when
        int actual = DiscountEvent.SPECIAL.calculateBenefitPrice(orders, VisitDate.from(3));
        int expected = 1000;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
