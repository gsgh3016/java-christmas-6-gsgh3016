package christmas.util;

import christmas.model.Menu;

import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OrderParser {
    private static final String SINGLE_ORDER_DELIMITER = "-";
    private static final String TOTAL_ORDER_DELIMITER = ",";

    public static final int MENU_INDEX = 0;
    public static final int QUANTITY_INDEX = 1;

    public static EnumMap<Menu, Integer> parse(String totalOrder) {
        EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
        List<String> totalOrderSplit = List.of(totalOrder.split(TOTAL_ORDER_DELIMITER));
        totalOrderSplit.forEach(
                singleOrder -> adaptSingleOrderParsing(singleOrder, orders)
        );
        return orders;
    }

    private static void adaptSingleOrderParsing(String singleOrder, EnumMap<Menu, Integer> orders) {
        Map.Entry<Menu, Integer> menuAndQuantity = extractMenuAndQuantity(singleOrder);
        orders.putIfAbsent(menuAndQuantity.getKey(), menuAndQuantity.getValue());
    }

    private static Map.Entry<Menu, Integer> extractMenuAndQuantity(String singleOrder) {
        List<String> singleOrderSplit = parseSingleOrder(singleOrder);
        Menu menu = Menu.select(singleOrderSplit.get(MENU_INDEX));
        int quantity = Integer.parseInt(singleOrderSplit.get(QUANTITY_INDEX));
        return new AbstractMap.SimpleEntry<>(menu, quantity);
    }

    private static List<String> parseSingleOrder(String singleOrder) {
        return List.of(singleOrder.split(SINGLE_ORDER_DELIMITER));
    }
}

