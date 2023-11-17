package christmas.service;

import christmas.model.Menu;
import christmas.util.constant.Category;
import christmas.view.OutputView;

import java.util.EnumMap;
import java.util.Map;

public class OrderPriceService {
    private final EnumMap<Menu, Integer> orders;
    private static final int TOTAL_PRICE_DEFAULT = 0;
    private int totalPrice;

    private OutputView outputView = new OutputView();

    public OrderPriceService(EnumMap<Menu, Integer> orders) {
        this.totalPrice = TOTAL_PRICE_DEFAULT;
        this.orders = orders;
    }

    public int getTotalPrice() {
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            Menu menu = order.getKey();
            int quantity = order.getValue();
            totalPrice += menu.getPrice() * quantity;
        }
        return totalPrice;
    }
}
