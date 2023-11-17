package christmas.service;

import christmas.model.Menu;
import christmas.util.constant.Category;
import christmas.model.DiscountManager;
import christmas.util.constant.Formatting;

public class GiftService {
    private static final int GIFT_POLICY = 120_000;
    private static final int NO_GIFT = 0;
    private static final String GIFT_DISCOUNT = "증정 이벤트";

    private static final Menu gift = Menu.CHAMPAGNE;
    private static final int GIFT_QUANTITY = 1;

    public int getGift(int totalPrice) {
        if (totalPrice >= GIFT_POLICY) {
            recordGift();
            return gift.getPrice() * GIFT_QUANTITY;
        }
        DiscountManager.add(Category.GIFT, Category.NO);
        return NO_GIFT;
    }

    private static void recordGift() {
        String formattedGift = String.format(Formatting.MENU, gift.getName(), GIFT_QUANTITY);
        DiscountManager.add(Category.GIFT, formattedGift);
        String formattedGiftPrice = String.format(Formatting.DISCOUNT, GIFT_DISCOUNT, -gift.getPrice() * GIFT_QUANTITY);
        DiscountManager.add(Category.DISCOUNT, formattedGiftPrice);
    }
}
