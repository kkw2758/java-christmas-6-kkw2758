package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.dto.OrderDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class EventApplierTest {
    private final Orders orders = Orders.of(List.of(
            OrderDto.of(Name.from("티본스테이크"), Count.from(1)),
            OrderDto.of(Name.from("바비큐립"), Count.from(1)),
            OrderDto.of(Name.from("초코케이크"), Count.from(2)),
            OrderDto.of(Name.from("제로콜라"), Count.from(1))));

    @DisplayName("평일(일요일 ~ 목요일) 할인 : 평일이면 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void applyWeekdayEventTest(int day) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();
        int dessertCount = 2;

        //when & then
        assertThat(benefitsInfo.get(Event.WEEKDAY_SALE)).isEqualTo(dessertCount * 2023);
    }

    @DisplayName("평일(일요일 ~ 목요일) 할인 : 평일이 아니면 디저트 메뉴를 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void notApplyWeekdayEventTest(int day) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();

        //when & then
        assertThat(benefitsInfo.containsKey(Event.WEEKDAY_SALE)).isEqualTo(false);
    }

    @DisplayName("주말(금요일, 토요일) 할인 : 주말이면 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void applyWeekendEventTest(int day) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        int mainCount = 2;
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();

        //when & then
        assertThat(benefitsInfo.get(Event.WEEKEND_SALE)).isEqualTo(mainCount * 2023);
    }

    @DisplayName("주말(금요일, 토요일) 할인 : 주말이 아니면 디저트 메뉴를 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void notApplyWeekendEventTest(int day) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();

        //when & then
        assertThat(benefitsInfo.containsKey(Event.WEEKEND_SALE)).isEqualTo(false);
    }

    @DisplayName("특별 할인 : 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applySpecialSaleEventTest(int day) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();

        //when & then
        assertThat(benefitsInfo.get(Event.SPECIAL_SLAE)).isEqualTo(1000);
    }

    @DisplayName("특별 할인 : 이벤트 달력에 별이 없으면 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9})
    void notApplySpecialSaleEventTest(int day) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();

        //when & then
        assertThat(benefitsInfo.containsKey(Event.SPECIAL_SLAE)).isEqualTo(false);
    }

    @DisplayName("크리스마스 디데이 할인 : 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 1000", "25, 3400"}, delimiter = ',')
    void applyChristmasSaleEventTest(int day, int expectedSaleAmount) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();

        //when & then
        assertThat(benefitsInfo.get(Event.CHRISTMAS_SALE)).isEqualTo(expectedSaleAmount);
    }

    @DisplayName("크리스마스 디데이 할인 : 크리스마스가 지나면 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void notApplyChristmasSaleEventTest(int day) {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(day));
        Map<Event, Integer> benefitsInfo = eventApplier.getBenefitsInfo();

        //when & then
        assertThat(benefitsInfo.containsKey(Event.CHRISTMAS_SALE)).isEqualTo(false);
    }

    @DisplayName("증정 이벤트 : 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.")
    @Test
    void applyGiftEventTest() {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(1));

        //when & then
        assertThat(eventApplier.getGiftMenu()).isEqualTo(Map.of(Menu.CHAMPAGNE, 1));
    }

    @DisplayName("증정 이벤트 : 총 주문 금액이 12만원이 안된다면 아무것도 증정하지 않는다.")
    @Test
    void notApplyGiftEventTest() {
        //given
        Orders orders = Orders.of(List.of(
                OrderDto.of(Name.from("티본스테이크"), Count.from(1)),
                OrderDto.of(Name.from("제로콜라"), Count.from(1))));
        EventApplier eventApplier = EventApplier.of(orders, Day.of(1));

        //when & then
        assertThat(eventApplier.getGiftMenu().size()).isEqualTo(0);
    }

    @DisplayName("할인 금액의 합계를 구한다.")
    @Test
    void calculateTotalSalePriceTest() {
        //given
        EventApplier eventApplier = EventApplier.of(orders, Day.of(3));

        //when & then
        assertThat(eventApplier.calculateTotalSalePrice()).isEqualTo(31246);
    }
}
