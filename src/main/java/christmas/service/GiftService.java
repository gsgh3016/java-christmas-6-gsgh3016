package christmas.service;

import christmas.model.Menu;
import christmas.util.Category;
import christmas.util.DiscountManager;
import christmas.util.Formatting;

public class GiftService {
    private static final int GIFT_POLICY = 120_000;
    private static final int NO_GIFT = 0;
    private static final Menu gift = Menu.CHAMPAGNE;
    private static final int quantity = 1;

    public int getGift(int totalPrice) {
        if (totalPrice >= GIFT_POLICY){
            String formattedGift = String.format(Formatting.MENU, gift.getName(), quantity);
            DiscountManager.add(Category.GIFT, formattedGift);
            return gift.getPrice() * quantity;
        }
        DiscountManager.add(Category.GIFT, Category.NO);
        return NO_GIFT;
    }
}
