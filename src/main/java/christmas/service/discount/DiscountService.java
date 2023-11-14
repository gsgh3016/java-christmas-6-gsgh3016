package christmas.service.discount;

import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.Category;
import christmas.util.DiscountManager;
import christmas.util.Formatting;

import java.util.EnumMap;
import java.util.Map;

public class DiscountService {
    private static final int DISCOUNT_POLICY = 10_000;
    private static final int NO_DISCOUNT = 0;

    private final DiscountStrategy weekDiscountStrategy;
    private final DiscountStrategy dDayDiscountStrategy;

    private final MyDate date;
    private final EnumMap<Menu, Integer> orders;

    public DiscountService(MyDate date, EnumMap<Menu, Integer> orders) {
        this.date = date;
        this.orders = orders;
        weekDiscountStrategy = getWeekDiscountStrategy();
        dDayDiscountStrategy = getDDayDiscountStrategy();
    }

    public int calculateDiscount(int totalPrice) {
        int discount = NO_DISCOUNT;
        if (totalPrice < DISCOUNT_POLICY) {
            DiscountManager.add(Category.DISCOUNT, Category.NO);
            return discount;
        }
        discount += applyDDayDiscount();
        discount += applyWeekDiscount();
        discount += applySpecialDiscount();
        recordDiscount(discount);
        return discount;
    }

    private DiscountStrategy getWeekDiscountStrategy() {
        if (date.isWeekEnd()) {
            return SingleOrderDiscountService.WEEKEND_DISCOUNT_STRATEGY;
        }
        return SingleOrderDiscountService.WEEKDAY_DISCOUNT_STRATEGY;
    }

    public int applyWeekDiscount() {
        int discountSum = NO_DISCOUNT;
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            discountSum = weekDiscountStrategy.apply(discountSum, order);
        }
        return discountSum;
    }

    private DiscountStrategy getDDayDiscountStrategy() {
        if (date.isDDayPeriod()) {
            return DDayDiscountService.DDAY_DISCOUNT_STRATEGY;
        }
        return null;
    }

    public int applyDDayDiscount() {
        if (dDayDiscountStrategy == null) { return NO_DISCOUNT; }
        return dDayDiscountStrategy.apply(NO_DISCOUNT, date);
    }

    private DiscountStrategy getSpecialDiscountStrategy() {
        return null;
    }

    public int applySpecialDiscount() {
        return NO_DISCOUNT;
    }

    private void recordDiscount(int discount) {
        String formattedPrice = String.format(Formatting.PRICE, -discount);
        DiscountManager.add(Category.DISCOUNT_PRICE, formattedPrice);
    }
}
