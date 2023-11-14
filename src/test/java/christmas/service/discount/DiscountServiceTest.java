package christmas.service.discount;

import christmas.service.ServiceTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountServiceTest {
    private DiscountService discountService;
    private ServiceTestData testData;
    private int expectedDiscount;

    @BeforeEach
    void setUp() {
        testData = new ServiceTestData();
        discountService = new DiscountService(testData.date, testData.orders);
        expectedDiscount = 2023 * 1 + (15 - 1) * 100 + 1000;
    }

    @Test
    void calculateDiscount_test() {
        assertThat(discountService.calculateDiscount(15_000))
                .isEqualTo(expectedDiscount);
    }
}
