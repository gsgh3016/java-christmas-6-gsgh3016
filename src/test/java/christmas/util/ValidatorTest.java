package christmas.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {
    private static Stream<Arguments> initTestData() {
        return Stream.of(
                Arguments.of("123", true),
                Arguments.of("456", true),
                Arguments.of("abc", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("initTestData")
    void checkNumber_test(String input, boolean isValid) {
        if (isValid) {
            assertThat(Validator.checkNumber(input)).isEqualTo(input);
            return;
        }
        assertThatThrownBy(() -> Validator.checkNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
