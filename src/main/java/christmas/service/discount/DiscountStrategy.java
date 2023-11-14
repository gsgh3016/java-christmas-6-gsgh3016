package christmas.service.discount;

@FunctionalInterface
public interface DiscountStrategy {
    int apply(int discountSum, Object target);
}
