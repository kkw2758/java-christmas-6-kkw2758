package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {
    @DisplayName("총 혜택 금액에 맞는 배지를 부여한다.")
    @Test
    void badgeTest() {
        // given & when & then
        assertAll(
                () -> assertThat(Badge.from(2_000).getName()).isEqualTo("없음"),
                () -> assertThat(Badge.from(5_000).getName()).isEqualTo("별"),
                () -> assertThat(Badge.from(10_000).getName()).isEqualTo("트리"),
                () -> assertThat(Badge.from(20_000).getName()).isEqualTo("산타")
        );
    }
}