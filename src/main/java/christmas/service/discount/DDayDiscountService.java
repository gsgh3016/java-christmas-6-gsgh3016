package christmas.service.discount;

import christmas.model.DiscountManager;
import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.constant.Category;
import christmas.util.constant.Formatting;

import java.util.EnumMap;
import java.util.function.Predicate;

public class DDayDiscountService extends DiscountService {
    private static final String DDAY_DISCOUNT = "크리스마스 디데이 할인";

    private static final int DAY_DISCOUNT_DEFAULT = 1_000;
    private static final int DAY_OFFSET = 1;
    private static final int DAY_DISCOUNT_UNIT = 100;
    private static final int NO_DISCOUNT = 0;

    public DDayDiscountService(MyDate date, EnumMap<Menu, Integer> orders) {
        super(date, orders);
    }

    public static int applyDiscount(MyDate date, Predicate<MyDate> condition) {
        int discountSum = NO_DISCOUNT;
        if (condition.test(date)) {
            int discountAmount = DAY_DISCOUNT_UNIT * (date.getDayOfMonth() - DAY_OFFSET);
            discountSum += discountAmount;
            recordDiscount(DAY_DISCOUNT_DEFAULT + discountAmount);
        }
        return DAY_DISCOUNT_DEFAULT + discountSum;
    }

    private static void recordDiscount(int discountAmount) {
        String detail = String.format(Formatting.DISCOUNT, DDAY_DISCOUNT, -discountAmount);
        DiscountManager.add(Category.DISCOUNT, detail);
    }

    public static final DiscountStrategy DDAY_DISCOUNT_STRATEGY =
            date -> applyDiscount((MyDate) date, MyDate::isDDayPeriod);;
}

