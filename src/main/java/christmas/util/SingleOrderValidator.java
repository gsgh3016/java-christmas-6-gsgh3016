package christmas.util;

import christmas.model.Menu;

import java.util.List;

import static christmas.util.TotalOrderValidator.checkDoubledOrder;

public class SingleOrderValidator {
    private static final int SINGLE_ORDER_MINIMUM = 0;
    private static final int TOTAL_ORDER_MAXIMUM = 20;
    private static final String SINGLE_ORDER_DELIMITER = "-";

    private static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static void checkMenuPresent(String menuOrder) {
        try {
            Menu.select(menuOrder);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(INVALID_ORDER);
        }
    }

    public static void checkSingleOrderQuantity(String quantityCommand) {
        int quantity;
        try {
            quantity = Integer.parseInt(quantityCommand);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(INVALID_ORDER);
        }
        if (quantity <= SINGLE_ORDER_MINIMUM || quantity > TOTAL_ORDER_MAXIMUM) {
            throw new IllegalStateException(INVALID_ORDER);
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
