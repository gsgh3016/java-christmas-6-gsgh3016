package christmas.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {
    enum STATE { NONE, STAR, TREE, SANTA }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(3_000, STATE.NONE),
                Arguments.of(7_000, STATE.STAR),
                Arguments.of(12_000, STATE.TREE),
                Arguments.of(22_000, STATE.SANTA)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void select_test(int discount, STATE state) {
        if (state == STATE.NONE) {
            assertThat(Badge.select(discount)).isEqualTo(Badge.NONE);
            return;
        }
        if (state == STATE.STAR) {
            assertThat(Badge.select(discount)).isEqualTo(Badge.STAR);
            return;
        }
        if (state == STATE.TREE) {
            assertThat(Badge.select(discount)).isEqualTo(Badge.TREE);
            return;
        }
        if (state == STATE.SANTA) {
            assertThat(Badge.select(discount)).isEqualTo(Badge.SANTA);
        }
    }
}
