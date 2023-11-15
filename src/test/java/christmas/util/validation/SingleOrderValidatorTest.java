package christmas.util.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SingleOrderValidatorTest {
    private SingleOrderValidator validator = new SingleOrderValidator();

    @Test
    void checkValidMenuPresent() {
        assertDoesNotThrow(
                () -> validator.checkMenuPresent("타파스")
        );
    }

    @Test
    void checkInvalidMenuPresent() {
        assertThatThrownBy(() -> validator.checkMenuPresent("invalidMenu"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "20"})
    void checkValidSingleOrderQuantity(String quantity) {
        assertDoesNotThrow(() -> validator.checkSingleOrderQuantity(quantity));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "21"})
    void checkInvalidSingleOrderQuantity(String quantity) {
        assertThatThrownBy(() -> validator.checkSingleOrderQuantity(quantity))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }
}
