package christmas.service;

import christmas.model.Menu;

import java.util.EnumMap;
import java.util.Map;

public class OrderPriceService {
    private final EnumMap<Menu, Integer> orders;
    private static final int TOTAL_PRICE_DEFAULT = 0;

    public OrderPriceService(EnumMap<Menu, Integer> orders) {
        this.orders = orders;
    }

    public int getTotalPrice() {
        int totalPrice = TOTAL_PRICE_DEFAULT;
        for (Map.Entry<Menu, Integer> order: orders.entrySet()) {
            Menu menu = order.getKey();
            int quantity = order.getValue();
            totalPrice += menu.getPrice() * quantity;
        }
        return totalPrice;
    }
}
