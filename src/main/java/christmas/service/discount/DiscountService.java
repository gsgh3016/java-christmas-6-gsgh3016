package christmas.service.discount;

import christmas.model.Menu;
import christmas.model.MyDate;

import java.util.EnumMap;
import java.util.Map;

public class DiscountService {
    private static final int DISCOUNT_POLICY = 10_000;
    private static final int STAR_DISCOUNT_AMOUNT = 1_000;
    private static final int NO_DISCOUNT_ADAPTED = 0;

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
        int discount = NO_DISCOUNT_ADAPTED;
        if (totalPrice < DISCOUNT_POLICY) {
            return discount;
        }
        discount += applyDDayDiscount();
        discount += applyWeekDiscount();
        discount += applySpecialDiscount();
        return discount;
    }

    private DiscountStrategy getWeekDiscountStrategy() {
        if (date.isWeekEnd()) {
            return SingleOrderDiscountService.WEEKEND_DISCOUNT_STRATEGY;
        }
        return SingleOrderDiscountService.WEEKDAY_DISCOUNT_STRATEGY;
    }

    public int applyWeekDiscount() {
        int discountSum = NO_DISCOUNT_ADAPTED;
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
        if (dDayDiscountStrategy == null) { return NO_DISCOUNT_ADAPTED; }
        return dDayDiscountStrategy.apply(NO_DISCOUNT_ADAPTED, date);
    }

    public int applySpecialDiscount() {
        if (date.isStar()) { return STAR_DISCOUNT_AMOUNT; }
        return NO_DISCOUNT_ADAPTED;
    }
}
