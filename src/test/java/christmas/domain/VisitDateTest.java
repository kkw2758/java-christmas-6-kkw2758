package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {
    @DisplayName("방문 날짜가 1이상 31이하가 아니라면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void visitDateExceptionTest(int visitDate) {
        // when & then
        assertThatThrownBy(() -> VisitDate.from(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문 날짜가 1이상 31이하가 라면 정상 동작한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 31})
    void visitDateSuccessTest(int visitDate) {
        // when & then
        Assertions.assertDoesNotThrow(() -> VisitDate.from(visitDate));
    }

    @DisplayName("주말(금요일 ~ 토요일) 이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void isWeekendTest(int visitDate) {
        // when & then
        assertTrue(() -> VisitDate.from(visitDate).isWeekend());
    }

    @DisplayName("평일(목요일 ~ 일요일) 이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void isWeekdayTest(int visitDate) {
        // when & then
        assertTrue(() -> VisitDate.from(visitDate).isWeekday());
    }

    @DisplayName("stat day(일요일 이거나 크리스마스) 이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void isStarDayTest(int visitDate) {
        // when & then
        assertTrue(() -> VisitDate.from(visitDate).isStarDay());
    }

    @DisplayName("크리스마스 디데이 이벤트 기간 이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 25})
    void isChristmasEventTargetTest(int visitDate) {
        // when & then
        assertTrue(() -> VisitDate.from(visitDate).isChristmasEventTarget());
    }
}