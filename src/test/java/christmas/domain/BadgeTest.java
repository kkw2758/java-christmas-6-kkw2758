package christmas.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {
    @DisplayName("총 혜택금액에 따라서 베지가 결정된다.")
    @Test
    void findBadgeTest() {
        // given & when & then
        assertAll(
                () -> assertThat(Badge.from(1_000)).isEqualTo(Badge.NONE),
                () -> assertThat(Badge.from(5_000)).isEqualTo(Badge.STAR),
                () -> assertThat(Badge.from(10_000)).isEqualTo(Badge.TREE),
                () -> assertThat(Badge.from(20_000)).isEqualTo(Badge.SANTA)
        );
    }
}
