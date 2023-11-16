package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayTest {
    @DisplayName("날짜가 1 이상 31이하의 숫자가 아니라면 예외 처리한다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {0, 32})
    void inValidDayTest(int day) {
        //when & then
        assertThatThrownBy(() -> Day.of(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DAY_OF_MONTH_ERROR.getMessage());
    }

    @DisplayName("날짜가 1 이상 31이하의 라면 정상 동작한다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {1, 20, 31})
    void validDayTest(int day) {
        //when & then
        Assertions.assertDoesNotThrow(() -> Day.of(day));
    }

    @DisplayName("특정 날짜에 별이 포함이되있다면 true를 반환한다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void validStarDayTest(int day) {
        //when & then
        assertThat(Day.of(day).getStar()).isEqualTo(true);
    }

    @DisplayName("특정 날짜에 별이 포함되어있지 않다면 false를 반환한다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {1, 2, 15, 26, 30})
    void invalidStarDayTest(int day) {
        //when & then
        assertThat(Day.of(day).getStar()).isEqualTo(false);
    }

    @DisplayName("특정 날짜가 주말(금요일, 토요일)이면 true를 반환한다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void validWeekendDayTest(int day) {
        //when & then
        assertThat(Day.of(day).isWeekend()).isEqualTo(true);
    }

    @DisplayName("특정 날짜가 주말(금요일, 토요일)이 아니면 false를 반환한다.")
    @ParameterizedTest
    //given
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void inValidWeekendDayTest(int day) {
        //when & then
        assertThat(Day.of(day).isWeekend()).isEqualTo(false);
    }
}
