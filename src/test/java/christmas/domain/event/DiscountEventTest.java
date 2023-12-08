package christmas.domain.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.exception.ErrorMessage;
import christmas.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountEventTest {
    @DisplayName("평일이고 디저트 메뉴를 가지고 있다면 평일 할인 이벤트 대상이다.")
    @Test
    void isWeekdayEventTargetTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when
        boolean actual = DiscountEvent.WEEKDAY.isEventTarget(VisitDate.from(4), orders);
        boolean expected = true;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("평일 할인 이벤트는 디저트 메뉴를 개당 2023원 할인한다.")
    @Test
    void calculateWeekdayBenefitPriceTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when
        int actual = DiscountEvent.WEEKDAY.calculateBenefitPrice(VisitDate.from(4), orders);
        int expected = 2023 * 2;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주말이고 메인 메뉴를 가지고 있다면 주말 할인 이벤트 대상이다.")
    @Test
    void isWeekendEventTargetTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when
        boolean actual = DiscountEvent.WEEKEND.isEventTarget(VisitDate.from(1), orders);
        boolean expected = true;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주말 할인 이벤트는 메인 메뉴를 개당 2023원 할인한다.")
    @Test
    void calculateWeekendBenefitPriceTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when
        int actual = DiscountEvent.WEEKEND.calculateBenefitPrice(VisitDate.from(4), orders);
        int expected = 2023;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("일요일이거나 크리스마스라면 특별 할인 이벤트 대상이다.")
    @Test
    void isSpecialEventTargetTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when & then
        assertAll(
                () -> assertTrue(DiscountEvent.SPECIAL.isEventTarget(VisitDate.from(25), orders)),
                () -> assertFalse(DiscountEvent.SPECIAL.isEventTarget(VisitDate.from(26), orders))
        );
    }

    @DisplayName("평일 할인 이벤트는 디저트 메뉴를 개당 2023원 할인한다.")
    @Test
    void calculateSpecialBenefitPriceTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when
        int actual = DiscountEvent.SPECIAL.calculateBenefitPrice(VisitDate.from(25), orders);
        int expected = 1000;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("방문 날짜가 12/1 ~ 12/25 라면 크리스마스 디데이 할인 대상이다.")
    @Test
    void isChristmasEventTargetTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when & then
        assertAll(
                () -> assertTrue(DiscountEvent.CHRISTMAS.isEventTarget(VisitDate.from(25), orders)),
                () -> assertFalse(DiscountEvent.CHRISTMAS.isEventTarget(VisitDate.from(26), orders))
        );
    }

    @DisplayName("평일 할인 이벤트는 디저트 메뉴를 개당 2023원 할인한다.")
    @Test
    void calculateChristmasBenefitPriceTest() {
        // given
        Orders orders = Orders.from(
                Parser.toOrdersRequest("티본스테이크-1,아이스크림-2", ErrorMessage.INVALID_ORDERS));

        // when
        int actual = DiscountEvent.CHRISTMAS.calculateBenefitPrice(VisitDate.from(25), orders);
        int expected = 3400;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}