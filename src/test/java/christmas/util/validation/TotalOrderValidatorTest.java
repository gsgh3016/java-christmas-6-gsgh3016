package christmas.util.validation;

import christmas.util.validation.TotalOrderValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TotalOrderValidatorTest {
    private TotalOrderValidator validator = new TotalOrderValidator();

    @Test
    void checkValidTotalOrder() {
        String validOrder = "타파스-1,해산물파스타-2";
        assertDoesNotThrow(() -> validator.checkTotalOrder(validOrder));
    }

    @Test
    void checkInvalidTotalOrder() {
        String invalidOrder = "invalidMenu-1,해산물파스타-2";
        assertThatThrownBy(() -> validator.checkTotalOrder(invalidOrder))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }

    @Test
    void checkEmptyTotalOrder() {
        assertThatThrownBy(() -> validator.checkTotalOrder(""))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("빈 주문입니다.");
    }

    @Test
    void checkDuplicatedTotalOrder() {
        String duplicatedOrder = "타파스-1,타파스-2";
        assertThatThrownBy(() -> validator.checkTotalOrder(duplicatedOrder))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }
}
