package christmas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyDateTest {
    private static Stream<Arguments> initTestData() {
        return Stream.of(
                Arguments.of("5", true),
                Arguments.of("32", false),
                Arguments.of("abc", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("initTestData")
    void MyDate_test(String input, boolean isValid) {
        if (isValid) {
            // given
            MyDate myDate = new MyDate(input);

            // when, then
            assertThat(myDate).isEqualTo(
                    LocalDate.of(2023, 12, Integer.parseInt(input))
            );
            return;
        }
        assertThatThrownBy(() -> new MyDate(input))
                .isInstanceOf(DateTimeException.class);
    }
}
