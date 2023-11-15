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

    public void printOrders() {
        OutputView.printEmptyLine();
        OutputView.printCategory(Category.ORDERED_MENU);
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            OutputView.printOrderDetail(order.getKey(), order.getValue());
        }
        OutputView.printEmptyLine();

        OutputView.printCategory(Category.TOTAL_PRICE_BEFORE_DISCOUNT);
        OutputView.printBeforeDiscount(totalPrice);
        OutputView.printEmptyLine();
    }
}
