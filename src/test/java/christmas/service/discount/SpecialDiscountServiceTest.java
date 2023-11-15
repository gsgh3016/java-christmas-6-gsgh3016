package christmas.service.discount;

import christmas.model.MyDate;
import christmas.service.ServiceTestData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialDiscountServiceTest {
    private ServiceTestData testData = new ServiceTestData();

    private static final int NO_DISCOUNT = 0;
    private static final int DISCOUNT = 1_000;

    void setUpNoDiscount() {
        testData.date = new MyDate("18");
    }

    void setUpDiscount() {
        testData.date = new MyDate("17");
    }

    @Test
    void applyNoSpecialDiscount_test() {
        setUpNoDiscount();

        int discount = SpecialDiscountService.SPECIAL_DISCOUNT_STRATEGY
                .apply(testData.date);

        assertThat(discount).isEqualTo(NO_DISCOUNT);
    }

    @Test
    void applySpecialDiscount_test() {
        setUpDiscount();

        int discount = SpecialDiscountService.SPECIAL_DISCOUNT_STRATEGY
                .apply(testData.date);

        assertThat(discount).isEqualTo(DISCOUNT);
    }
}
