package christmas.service.discount;

import christmas.service.ServiceTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DDayDiscountServiceTest {
    private ServiceTestData testData;

    private static final int DISCOUNT_UNIT = 100;
    private static final int DISCOUNT_DEFAULT = 1_000;
    private static final int DAY_OFFSET = 1;

    @BeforeEach
    void setUp() {
        testData = new ServiceTestData();
    }

    @Test
    void applyDDayDiscount_test() {
        int discountSum = 0;
        int expectedDiscount = DISCOUNT_DEFAULT + (testData.date.getDayOfMonth() - DAY_OFFSET) * DISCOUNT_UNIT;

        discountSum = DDayDiscountService.DDAY_DISCOUNT_STRATEGY
                .apply(testData.date);

        assertThat(discountSum).isEqualTo(expectedDiscount);
    }
}
