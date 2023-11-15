package christmas.util.validation;

import christmas.model.Menu;
import christmas.util.OrderParser;
import christmas.util.constant.ParsingDelimiter;

import java.util.ArrayList;
import java.util.List;

public class TotalOrderValidator {
    private static final int TOTAL_ORDER_MAXIMUM = 20;

    private final List<Menu> totalOrderedMenu = new ArrayList<>();
    private List<String> totalParsedOrders;
    private int totalOrderQuantity = 0;

    private final SingleOrderValidator singleOrderValidator = new SingleOrderValidator();

    public String checkTotalOrder(String totalOrder) {
        checkTotalOrderEmpty(totalOrder);
        totalParsedOrders = List.of(totalOrder.split(ParsingDelimiter.TOTAL_ORDER_DELIMITER));
        checkParsedOrders();
        checkDoubledOrder();
        checkTotalOrderQuantity();
        return totalOrder;
    }

    private void checkTotalOrderEmpty(String totalOrder) {
        if (totalOrder.isEmpty()) {
            throw new IllegalStateException(ErrorMessage.EMPTY_ORDER);
        }
    }

    private void checkParsedOrders() {
        for (String singleOrder : totalParsedOrders) {
            totalOrderQuantity += singleOrderValidator.checkSingleOrder(singleOrder);
        }
    }

    private void checkDoubledOrder() {
        for (String singleOrder : totalParsedOrders) {
            List<String> parsedSingleOrder =
                    List.of(singleOrder.split(ParsingDelimiter.SINGLE_ORDER_DELIMITER));
            Menu menu = Menu.select(parsedSingleOrder.get(OrderParser.MENU_INDEX));
            if (totalOrderedMenu.contains(menu)) {
                throw new IllegalStateException(ErrorMessage.INVALID_ORDER);
            }
            totalOrderedMenu.add(menu);
        }
    }

    private void checkTotalOrderQuantity() {
        if (totalOrderQuantity > TOTAL_ORDER_MAXIMUM) {
            throw new IllegalStateException(ErrorMessage.INVALID_ORDER);
        }
    }
}
