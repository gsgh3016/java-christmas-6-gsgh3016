package christmas.service.discount;

import christmas.service.ServiceTestData;
import christmas.service.discount.DDayDiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DDayDiscountServiceTest {
    private DDayDiscountService discountService;
    private ServiceTestData testData;

    private static final int DISCOUNT_UNIT = 100;
    private static final int DISCOUNT_DEFAULT = 1_000;
    private static final int DAY_OFFSET = 1;

    @BeforeEach
    void setUp() {
        discountService = new DDayDiscountService(testData.date, testData.orders);
        testData = new ServiceTestData();
    }

    @Test
    void applyDDayDiscount_test() {
        int discountSum = 0;
        int expectedDiscount = DISCOUNT_DEFAULT + (testData.date.getDayOfMonth() - DAY_OFFSET) * DISCOUNT_UNIT;

        discountSum = DDayDiscountService.DDAY_DISCOUNT_STRATEGY
                .apply(discountSum, testData.date);

        assertThat(discountSum).isEqualTo(expectedDiscount);
    }
}
