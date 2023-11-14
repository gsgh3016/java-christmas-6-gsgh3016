package christmas.service;

import christmas.service.discount.DiscountService;
import christmas.util.Badge;
import christmas.model.Menu;
import christmas.model.MyDate;

import java.util.EnumMap;

public class PaymentService {
    private Badge badge;

    private final OrderPriceService orderPriceService;
    private final DiscountService discountService;
    private final GiftService giftService;

    public PaymentService(MyDate date, EnumMap<Menu, Integer> orders) {
        orderPriceService = new OrderPriceService(orders);
        discountService = new DiscountService(date, orders);
        giftService = new GiftService();
    }

    public void pay(int totalPrice) {
        int discount = applyDiscount(totalPrice);
        discount += applyGift(totalPrice);
        applyBadge(discount);
    }

    public int applyDiscount(int totalPrice) {
        return discountService.calculateDiscount(totalPrice);
    }


    private int applyGift(int totalPrice) {
        return giftService.getGift(totalPrice);
    }

    private void applyBadge(int discount) {
    }
}
