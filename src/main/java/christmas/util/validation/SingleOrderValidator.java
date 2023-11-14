package christmas.util.validation;

import christmas.model.Menu;

import java.util.List;

import static christmas.util.validation.TotalOrderValidator.checkDoubledOrder;

public class SingleOrderValidator {
    private static final int SINGLE_ORDER_MINIMUM = 0;
    private static final int TOTAL_ORDER_MAXIMUM = 20;
    private static final String SINGLE_ORDER_DELIMITER = "-";

    public static void checkMenuPresent(String menuOrder) {
        try {
            Menu.select(menuOrder);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(ErrorMessage.INVALID_ORDER);
        }
    }

    public static void checkSingleOrderQuantity(String quantityCommand) {
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

    public static int checkSingleOrder(String singleOrder, int totalOrderQuantity) {
        List<String> singleParsedOrder = List.of(singleOrder.split(SINGLE_ORDER_DELIMITER));
        checkMenuPresent(singleParsedOrder.get(0));
        checkDoubledOrder(singleParsedOrder.get(0));
        checkSingleOrderQuantity(singleParsedOrder.get(1));
        return totalOrderQuantity + Integer.parseInt(singleParsedOrder.get(1));
    }
}
