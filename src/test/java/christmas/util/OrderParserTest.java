package christmas.util;

import christmas.model.Menu;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EnumMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderParserTest {
    enum STATE {
        CORRECT,
        INVALID_FORMAT,
        NON_EXISTENT_MENU,
        INVALID_QUANTITY,
        EMPTY
    }

    private static Stream<Arguments> orderParsingTestData() {
        return Stream.of(
                Arguments.of("타파스-2,해산물파스타-1", STATE.CORRECT),
                Arguments.of("타파스 2, 해산물파스타 1", STATE.INVALID_FORMAT),
                Arguments.of("없는메뉴-1,해산물파스타-1", STATE.NON_EXISTENT_MENU),
                Arguments.of("", STATE.EMPTY)
        );
    }

    @ParameterizedTest
    @MethodSource("orderParsingTestData")
    void parse_test(String order, STATE state) {
        if (state == STATE.CORRECT) {
            EnumMap<Menu, Integer> parsedOrder = OrderParser.parse(order);
            assertThat(parsedOrder).isNotNull();
            assertThat(parsedOrder).isNotEmpty();
            return;
        }
        assertThatThrownBy(() -> OrderParser.parse(order))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

