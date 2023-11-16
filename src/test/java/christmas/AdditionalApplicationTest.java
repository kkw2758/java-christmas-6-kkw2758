package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class AdditionalApplicationTest extends NsTest {
    @Test
    void 증정_메뉴_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("없음");
        });
    }

    @Test
    void 날짜_공백_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException("", "타파스-1,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_구분자_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException("7", ",");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_공백_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException("7", "\n");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
