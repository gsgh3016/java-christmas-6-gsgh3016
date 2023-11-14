package christmas.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TotalOrderValidatorTest {

    @Test
    void checkValidTotalOrder() {
        String validOrder = "타파스-1,해산물파스타-2";
        assertDoesNotThrow(() -> TotalOrderValidator.checkTotalOrder(validOrder));
    }

    @Test
    void checkInvalidTotalOrder() {
        String invalidOrder = "invalidMenu-1,해산물파스타-2";
        assertThatThrownBy(() -> TotalOrderValidator.checkTotalOrder(invalidOrder))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }

    @Test
    void checkEmptyTotalOrder() {
        assertThatThrownBy(() -> TotalOrderValidator.checkTotalOrder(""))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("빈 주문입니다.");
    }

    @Test
    void checkDuplicatedTotalOrder() {
        String duplicatedOrder = "타파스-1,타파스-2";
        assertThatThrownBy(() -> TotalOrderValidator.checkTotalOrder(duplicatedOrder))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("유효하지 않은 주문입니다.");
    }
}
