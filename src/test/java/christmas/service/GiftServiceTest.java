package christmas.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GiftServiceTest {
    private static Stream<Arguments> setTestData() {
        return Stream.of(
                Arguments.of(10_000, false),
                Arguments.of(120_000, true)
        );
    }

    @ParameterizedTest
    @MethodSource("setTestData")
    void getGift_test(int totalPrice, boolean isGift) {
        GiftService giftService = new GiftService();
        if (isGift) {
            assertThat(giftService.getGift(totalPrice)).isEqualTo(25_000);
            return;
        }
        assertThat(giftService.getGift(totalPrice)).isZero();
    }
}
