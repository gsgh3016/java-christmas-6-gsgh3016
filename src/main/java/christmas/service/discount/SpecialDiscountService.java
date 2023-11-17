package christmas.service.discount;

import christmas.model.MyDate;
import christmas.util.constant.Category;
import christmas.model.DiscountManager;
import christmas.util.constant.Formatting;

import java.util.function.Predicate;

public class SpecialDiscountService {
    private static final int STAR_DISCOUNT_AMOUNT = 1_000;
    private static final int NO_DISCOUNT = 0;
    private static final String SPECIAL_DISCOUNT = "특별 할인";

    public static int applyDiscount(MyDate date, Predicate<MyDate> condition) {
        if (condition.test(date)) {
            recordDiscount();
            return STAR_DISCOUNT_AMOUNT;
        }
        return NO_DISCOUNT;
    }

    private static void recordDiscount() {
        String detail = String.format(Formatting.DISCOUNT, SPECIAL_DISCOUNT, -STAR_DISCOUNT_AMOUNT);
        DiscountManager.add(Category.DISCOUNT, detail);
    }

    public static final DiscountStrategy SPECIAL_DISCOUNT_STRATEGY =
            date -> applyDiscount((MyDate) date, MyDate::isStar);
}
