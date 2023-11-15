package christmas.service.discount;

import christmas.model.DiscountManager;
import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.constant.Category;
import christmas.util.constant.Formatting;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Predicate;

public class SingleOrderDiscountService extends DiscountService {
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";

    private static final int WEEK_DISCOUNT_UNIT = 2_023;
    private static final int NO_DISCOUNT = 0;

    public SingleOrderDiscountService(MyDate date, EnumMap<Menu, Integer> orders) {
        super(date, orders);
    }

    public static int applyDiscount(Map.Entry<Menu, Integer> order, Predicate<Menu> condition, String discountLabel) {
        int discountSum = NO_DISCOUNT;
        if (condition.test(order.getKey())) {
            int discountAmount = WEEK_DISCOUNT_UNIT * order.getValue();
            discountSum += discountAmount;
            recordDiscount(discountLabel, discountAmount);
        }
        return discountSum;
    }

    private static void recordDiscount(String discountLabel, int discountAmount) {
        String detail = String.format(Formatting.DISCOUNT, discountLabel, -discountAmount);
        DiscountManager.add(Category.DISCOUNT, detail);
    }

    public static final DiscountStrategy WEEKEND_DISCOUNT_STRATEGY =
            order -> applyDiscount((Map.Entry<Menu, Integer>) order, Menu::isMain, WEEKEND_DISCOUNT);

    public static final DiscountStrategy WEEKDAY_DISCOUNT_STRATEGY =
            order -> applyDiscount((Map.Entry<Menu, Integer>) order, Menu::isDessert, WEEKDAY_DISCOUNT);

}
