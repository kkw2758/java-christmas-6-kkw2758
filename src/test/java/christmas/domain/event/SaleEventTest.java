package christmas.domain.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Count;
import christmas.domain.Day;
import christmas.domain.Name;
import christmas.domain.Orders;
import christmas.domain.dto.OrderDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SaleEventTest {
    private final Orders orders = Orders.of(List.of(
                    OrderDto.of(Name.from("티본스테이크"), Count.from(1)),
                    OrderDto.of(Name.from("바비큐립"), Count.from(1)),
                    OrderDto.of(Name.from("초코케이크"), Count.from(2)),
                    OrderDto.of(Name.from("제로콜라"), Count.from(1))
            )
    );

    @DisplayName("평일(일요일 ~ 목요일) 할인 : 평일이면 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void applyWeekdayEventTest(int day) {
        //given
        Map<Event, Integer> benefitInfo = SaleEvent.WEEKDAY_SALE.getBenefitInfo(orders, Day.of(day));
        int dessertCount = 2;

        //when & then
        assertThat(benefitInfo.get(SaleEvent.WEEKDAY_SALE)).isEqualTo(dessertCount * 2023);
    }

    @DisplayName("평일(일요일 ~ 목요일) 할인 : 평일이 아니면 디저트 메뉴를 할인하지 않는다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {8, 9})
    void notApplyWeekdayEventTest(int day) {
        //when & then
        assertThat(SaleEvent.WEEKDAY_SALE.getBenefitInfo(orders, Day.of(day))).isEqualTo(Map.of());
    }

    @DisplayName("주말(금요일, 토요일) 할인 : 주말이면 메인 메뉴를 메뉴 1개당 2,023원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void applyWeekendEventTest(int day) {
        //given
        Map<Event, Integer> benefitInfo = SaleEvent.WEEKEND_SALE.getBenefitInfo(orders, Day.of(day));
        int mainCount = 2;

        //when & then
        assertThat(benefitInfo.get(SaleEvent.WEEKEND_SALE)).isEqualTo(mainCount * 2023);
    }

    @DisplayName("주말(금요일, 토요일) 할인 : 주말이 아니면 메인 메뉴를 할인하지 않는다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void notApplyWeekendEventTest(int day) {
        //when & then
        assertThat(SaleEvent.WEEKEND_SALE.getBenefitInfo(orders, Day.of(day))).isEqualTo(Map.of());
    }

    @DisplayName("특별 할인 : 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applySpecialSaleEventTest(int day) {
        //given
        Map<Event, Integer> benefitInfo = SaleEvent.SPECIAL_SALE.getBenefitInfo(orders, Day.of(day));

        //when & then
        assertThat(benefitInfo.get(SaleEvent.SPECIAL_SALE)).isEqualTo(1000);
    }

    @DisplayName("특별 할인 : 이벤트 달력에 별이 없으면 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 9})
    void notApplySpecialSaleEventTest(int day) {
        //when & then
        assertThat(SaleEvent.SPECIAL_SALE.getBenefitInfo(orders, Day.of(day))).isEqualTo(Map.of());
    }

    @DisplayName("크리스마스 디데이 할인 : 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 1000", "25, 3400"}, delimiter = ',')
    void applyChristmasSaleEventTest(int day, int expectedSaleAmount) {
        //given
        Map<Event, Integer> benefitInfo = SaleEvent.CHRISTMAS_SALE.getBenefitInfo(orders, Day.of(day));

        //when & then
        assertThat(benefitInfo.get(SaleEvent.CHRISTMAS_SALE)).isEqualTo(expectedSaleAmount);
    }

    @DisplayName("크리스마스 디데이 할인 : 크리스마스가 지나면 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void notApplyChristmasSaleEventTest(int day) {
        //when & then
        assertThat(SaleEvent.CHRISTMAS_SALE.getBenefitInfo(orders, Day.of(day))).isEqualTo(Map.of());
    }
}
