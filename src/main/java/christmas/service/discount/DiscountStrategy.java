package christmas.service.discount;

@FunctionalInterface
public interface DiscountStrategy {
    int apply(Object target);
}
