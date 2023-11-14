package christmas.util.validation;

import christmas.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static christmas.util.validation.SingleOrderValidator.checkSingleOrder;

public class TotalOrderValidator {
    private static final int TOTAL_ORDER_MAXIMUM = 20;
    private static final String TOTAL_ORDER_DELIMITER = ",";
    private static final String EMPTY_ORDER = "빈 주문입니다.";
    private static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final List<Menu> totalOrderedMenu = new ArrayList<>();

    public static String checkTotalOrder(String totalOrder) {
        checkTotalOrderEmpty(totalOrder);
        List<String> totalParsedOrders = List.of(totalOrder.split(TOTAL_ORDER_DELIMITER));
        int totalOrderQuantity = 0;
        for (String singleOrder : totalParsedOrders) {
            totalOrderQuantity = checkSingleOrder(singleOrder, totalOrderQuantity);
        }
        checkTotalOrderQuantity(totalOrderQuantity);
        return totalOrder;
    }

    private static void checkTotalOrderQuantity(int totalOrderQuantity) {
        if (totalOrderQuantity > TOTAL_ORDER_MAXIMUM) {
            throw new IllegalStateException(INVALID_ORDER);
        }
    }

    private static void checkTotalOrderEmpty(String totalOrder) {
        if (totalOrder.isEmpty()) {
            throw new IllegalStateException(EMPTY_ORDER);
        }
    }

    public static void checkDoubledOrder(String name) {
        Menu menu = Menu.select(name);
        if (totalOrderedMenu.contains(menu)) {
            throw new IllegalStateException(INVALID_ORDER);
        }
        totalOrderedMenu.add(menu);
    }
}
