package christmas.util.validation;

import christmas.util.validation.DateValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "15", "25"})
    void checkValidDay(String day) {
        assertThat(DateValidator.checkDay(day)).isEqualTo(day);
    }

    @Test
    void checkEmptyDay() {
        assertThatThrownBy(() -> DateValidator.checkDay(""))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("빈 날짜입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"32", "abc"})
    void checkInvalidDay(String day) {
        assertThatThrownBy(() -> DateValidator.checkDay(day))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
