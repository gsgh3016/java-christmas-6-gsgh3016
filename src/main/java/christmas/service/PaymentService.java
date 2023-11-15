package christmas.service;

import christmas.model.DiscountManager;
import christmas.service.discount.DiscountService;
import christmas.model.Badge;
import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.constant.Category;
import christmas.util.constant.Formatting;
import christmas.util.validation.ErrorMessage;
import christmas.view.ErrorView;

import java.util.EnumMap;

public class PaymentService {
    private final DiscountService discountService;
    private final GiftService giftService;

    private static final int DISCOUNT_DEFAULT = 0;

    public PaymentService(MyDate date, EnumMap<Menu, Integer> orders) {
        discountService = new DiscountService(date, orders);
        giftService = new GiftService();
    }

    public void pay(int totalPrice) {
        int discount = DISCOUNT_DEFAULT;
        try {
            discount += applyDiscount(totalPrice);
        } catch (IllegalStateException illegalStateException) {
            ErrorView.print(illegalStateException.getMessage());
        }
        applyAfterDiscount(totalPrice, discount);
        discount += applyGift(totalPrice);
        recordDiscount(discount);
        applyBadge(discount);
    }

    private int applyDiscount(int totalPrice) {
        int discount = discountService.calculateDiscount(totalPrice);
        if (discount >= totalPrice) {
            throw new IllegalStateException(ErrorMessage.DISCOUNT_ERROR);
        }
        return discount;
    }


    private int applyGift(int totalPrice) {
        return giftService.getGift(totalPrice);
    }

    private void applyAfterDiscount(int totalPrice, int discount) {
        String formattedPrice = String.format(Formatting.PRICE, totalPrice - discount);
        DiscountManager.add(Category.TOTAL_PRICE_AFTER_DISCOUNT, formattedPrice);
    }

    private void applyBadge(int discount) {
        DiscountManager.add(Category.BADGE, Badge.select(discount).toString());
    }

    private void recordDiscount(int discount) {
        if (discount != 0) {
            String formattedPrice = String.format(Formatting.PRICE, -discount);
            DiscountManager.add(Category.DISCOUNT_PRICE, formattedPrice);
        }
    }
}
