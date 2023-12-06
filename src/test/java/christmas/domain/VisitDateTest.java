package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {
    @DisplayName("방문 날짜가 1 이상 31이하가 아니라면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {0,32})
    void visitDateExceptionTest(int visitDate) {
        // when & then
        assertThatThrownBy(() -> VisitDate.from(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문 날짜가 1 이상 31이하 라면 정상 동작 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1,10, 31})
    void visitDateSuccessTest(int visitDate) {
        // when & then
        assertDoesNotThrow(() -> VisitDate.from(visitDate));
    }
}
