package christmas.service;

import christmas.model.Menu;
import christmas.util.Category;
import christmas.util.DiscountManager;
import christmas.util.Formatting;
import christmas.view.OutputView;

public class GiftService {
    private static final int GIFT_POLICY = 120_000;
    private static final int NO_GIFT = 0;
    private static final Menu gift = Menu.CHAMPAGNE;
    private static final int quantity = 1;
    private boolean isGift = false;

    public int getGift(int totalPrice) {
        if (totalPrice >= GIFT_POLICY){
            isGift = true;
            return gift.getPrice() * quantity;
        }
        return NO_GIFT;
    }

    public void applyGift() {
        if (!isGift) {
            DiscountManager.add(Category.GIFT, Category.NO);
            return;
        }
        String formattedMenu = String.format(Formatting.MENU, gift.getName(), quantity);
        DiscountManager.add(Category.GIFT, formattedMenu);
    }
}
