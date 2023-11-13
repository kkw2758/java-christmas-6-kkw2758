package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.dto.OrderDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultTest {
    private final Orders orders = Orders.of(List.of(
                    OrderDto.of(Name.from("티본스테이크"), Count.from(1)),
                    OrderDto.of(Name.from("바비큐립"), Count.from(1)),
                    OrderDto.of(Name.from("초코케이크"), Count.from(2)),
                    OrderDto.of(Name.from("제로콜라"), Count.from(1))
            )
    );

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
