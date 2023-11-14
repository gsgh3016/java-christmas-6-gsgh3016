package christmas.service;

@FunctionalInterface
public interface DiscountStrategy {
    int apply(int discountSum, Object target);
}
