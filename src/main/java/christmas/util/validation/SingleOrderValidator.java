package christmas.util.validation;

import christmas.model.Menu;
import christmas.util.constant.ParsingDelimiter;

import java.util.List;

public class SingleOrderValidator {
    private static final int SINGLE_ORDER_MINIMUM = 0;
    private static final int TOTAL_ORDER_MAXIMUM = 20;

    public int checkSingleOrder(String singleOrder) {
        List<String> singleParsedOrder =
                List.of(singleOrder.split(ParsingDelimiter.SINGLE_ORDER_DELIMITER));
        checkMenuPresent(singleParsedOrder.get(0));
        checkSingleOrderQuantity(singleParsedOrder.get(1));
        return Integer.parseInt(singleParsedOrder.get(1));
    }

    public void checkMenuPresent(String menuOrder) {
        try {
            Menu.select(menuOrder);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(ErrorMessage.INVALID_ORDER);
        }
    }

    public void checkSingleOrderQuantity(String quantityCommand) {
        int quantity;
        try {
            quantity = Integer.parseInt(quantityCommand);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(ErrorMessage.INVALID_ORDER);
        }
        if (quantity <= SINGLE_ORDER_MINIMUM || quantity > TOTAL_ORDER_MAXIMUM) {
            throw new IllegalStateException(ErrorMessage.INVALID_ORDER);
        }
    }
}
