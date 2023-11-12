package christmas.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {
    private static Stream<Arguments> initTestData() {
        return Stream.of(
                Arguments.of("아이스크림", true),
                Arguments.of("우테코탈락", false)
        );
    }

    @ParameterizedTest
    @MethodSource("initTestData")
    void select_test(String order, boolean isValid) {
        if (isValid) {
            assertThat(Menu.select(order))
                    .isEqualTo(Menu.ICE_CREAM);
            return;
        }
        assertThatThrownBy(() -> Menu.select(order))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
