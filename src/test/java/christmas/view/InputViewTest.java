package christmas.view;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest extends NsTest {

    @Test
    @DisplayName("유효한 날짜 입력 시 정상 처리 테스트")
    void testValidDateInput() {
        assertSimpleTest(() -> {
            run("5");
            assertThat(InputView.inputDate()).isEqualTo(LocalDate.parse("2023-12-05"));
        });
    }

    @Test
    @DisplayName("유효하지 않은 날짜 입력 시 오류 메시지 출력 테스트")
    void testInvalidDateInput() {
        assertSimpleTest(() -> {
            run("32", "5");
            InputView.inputDate();
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("숫자가 아닌 입력 처리 테스트")
    void testNonNumericInput() {
        assertSimpleTest(() -> {
            run("abc", "5");
            InputView.inputDate();
            assertThat(output()).contains("[ERROR] 숫자만 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("빈 입력 처리 테스트")
    void testEmptyInput() {
        assertSimpleTest(() -> {
            run("", "5");
            InputView.inputDate();
            assertThat(output()).contains("[ERROR] 숫자만 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
