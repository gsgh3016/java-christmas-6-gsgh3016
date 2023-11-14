package christmas.service;

import christmas.model.Menu;

public class GiftService {
    private static final int GIFT_POLICY = 120_000;
    private static final int NO_GIFT = 0;
    private static final Menu gift = Menu.CHAMPAGNE;
    private static final int quantity = 1;

    public int getGift(int totalPrice) {
        if (totalPrice >= GIFT_POLICY){
            return gift.getPrice() * quantity;
        }
        return NO_GIFT;
    }
}
