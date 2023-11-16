package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {
    @DisplayName("입력한 총혜택 금액에 따라 그에 맞는 배지를 반환한다.")
    @ParameterizedTest
    //given
    @CsvSource(value = {"0, 없음","5000, 별", "10000, 트리", "20000, 산타"}, delimiter = ',')
    void findBadgeTest(int totalBenefitAmount, String badgeName) {
        //when & then
        assertThat(Badge.findBadge(totalBenefitAmount).getName()).isEqualTo(badgeName);
    }
}
