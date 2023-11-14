package christmas.service.discount;

import christmas.util.DiscountManager;
import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.Category;
import christmas.util.Formatting;

import java.util.EnumMap;
import java.util.function.Predicate;

public class DDayDiscountService extends DiscountService {
    private static final String DDAY_DISCOUNT = "크리스마스 디데이 할인";

    private static final int DAY_DISCOUNT_DEFAULT = 1_000;
    private static final int DAY_OFFSET = 1;
    private static final int DAY_DISCOUNT_UNIT = 100;

    public DDayDiscountService(MyDate date, EnumMap<Menu, Integer> orders) {
        super(date, orders);
    }

    public static int applyDiscount(int discountSum, MyDate date, Predicate<MyDate> condition, String discountLabel) {
        if (condition.test(date)) {
            int discountAmount = DAY_DISCOUNT_UNIT * (date.getDayOfMonth() - DAY_OFFSET);
            discountSum += discountAmount;
            recordDiscount(discountLabel, discountAmount);
        }
        return DAY_DISCOUNT_DEFAULT + discountSum;
    }

    private static void recordDiscount(String discountLabel, int discountAmount) {
        String detail = String.format(Formatting.DISCOUNT, discountLabel, -discountAmount);
        DiscountManager.add(Category.DISCOUNT, detail);
    }

    public static final DiscountStrategy DDAY_DISCOUNT_STRATEGY =
            (discountSum, date) -> applyDiscount(discountSum, (MyDate) date, MyDate::isDDayPeriod, DDAY_DISCOUNT);;
}

