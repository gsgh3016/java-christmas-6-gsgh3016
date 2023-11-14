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
    private final DiscountStrategy specialDiscountStrategy;

    private final MyDate date;
    private final EnumMap<Menu, Integer> orders;

    public DiscountService(MyDate date, EnumMap<Menu, Integer> orders) {
        this.date = date;
        this.orders = orders;
        weekDiscountStrategy = getWeekDiscountStrategy();
        dDayDiscountStrategy = getDDayDiscountStrategy();
        specialDiscountStrategy = getSpecialDiscountStrategy();
    }

    public int calculateDiscount(int totalPrice) {
        int discount = NO_DISCOUNT;
        if (totalPrice < DISCOUNT_POLICY) {
            recordNoDiscount();
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

    private int applyWeekDiscount() {
        int discountSum = NO_DISCOUNT;
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            discountSum += weekDiscountStrategy.apply(order);
        }
        return discountSum;
    }

    private DiscountStrategy getDDayDiscountStrategy() {
        return DDayDiscountService.DDAY_DISCOUNT_STRATEGY;
    }

    private int applyDDayDiscount() {
        return dDayDiscountStrategy.apply(date);
    }

    private DiscountStrategy getSpecialDiscountStrategy() {
        return SpecialDiscountService.SPECIAL_DISCOUNT_STRATEGY;
    }

    private int applySpecialDiscount() {
        return specialDiscountStrategy.apply(date);
    }

    private void recordNoDiscount() {
        DiscountManager.add(Category.DISCOUNT, Category.NO);
        DiscountManager.add(Category.GIFT, Category.NO);
        DiscountManager.add(Category.DISCOUNT_PRICE, Category.NO);
    }

    private void recordDiscount(int discount) {
        String formattedPrice = String.format(Formatting.PRICE, -discount);
        DiscountManager.add(Category.DISCOUNT_PRICE, formattedPrice);
    }
}
