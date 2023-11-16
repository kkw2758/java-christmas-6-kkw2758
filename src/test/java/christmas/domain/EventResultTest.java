package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultTest {
    private final Orders orders = Orders.of("티본스테이크-1, 바비큐립-1, 초코케이크-2, 제로콜라-1");

    @DisplayName("할인 금액의 합계를 계산한다.")
    @Test
    void calculateTotalSaleAmountTest() {
        //given
        EventResult eventResult = EventResult.of(orders, Day.of(3));

        //when & then
        assertThat(eventResult.calculateTotalSaleAmount()).isEqualTo(6246);
    }

    @DisplayName("총 혜택 금액을 계산한다.")
    @Test
    void calculateTotalBenefitAmountTest() {
        //given
        EventResult eventResult = EventResult.of(orders, Day.of(3));

        //when & then
        assertThat(eventResult.calculateTotalBenefitAmount()).isEqualTo(31246);
    }

    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    @Test
    void calculatePriceAfterSaleTest() {
        //given
        EventResult eventResult = EventResult.of(orders, Day.of(3));
        int priceBeforeSale = orders.calculatePriceBeforeSale();

        //when & then
        assertThat(eventResult.calculatePriceAfterSale(priceBeforeSale)).isEqualTo(135754);
    }
}
