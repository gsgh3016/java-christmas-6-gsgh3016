package christmas.service.discount;

import christmas.model.DiscountManager;
import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.Category;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Predicate;

public class SingleOrderDiscountService extends DiscountService {
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";

    private static final int WEEK_DISCOUNT_UNIT = 2_023;

    public SingleOrderDiscountService(MyDate date, EnumMap<Menu, Integer> orders) {
        super(date, orders);
    }

    public static int applyDiscount(int discountSum, Map.Entry<Menu, Integer> order, Predicate<Menu> condition, String discountLabel) {
        if (condition.test(order.getKey())) {
            int discountAmount = WEEK_DISCOUNT_UNIT * order.getValue();
            discountSum += discountAmount;
            String detail = String.format("%s: %d", discountLabel, discountAmount);
            DiscountManager.add(Category.DISCOUNT, detail);
        }
        return discountSum;
    }

    public static final DiscountStrategy WEEKEND_DISCOUNT_STRATEGY =
            (discountSum, order) -> applyDiscount(discountSum, (Map.Entry<Menu, Integer>) order, Menu::isMain, WEEKEND_DISCOUNT);

    public static final DiscountStrategy WEEKDAY_DISCOUNT_STRATEGY =
            (discountSum, order) -> applyDiscount(discountSum, (Map.Entry<Menu, Integer>) order, Menu::isDessert, WEEKDAY_DISCOUNT);

}
