package christmas.service.discount;

import christmas.model.Menu;
import christmas.service.ServiceTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SingleOrderDiscountServiceTest {
    private SingleOrderDiscountService discountService;
    private ServiceTestData testData;

    private static final int DISCOUNT_UNIT = 2_023;

    @BeforeEach
    void setUp() {
        testData = new ServiceTestData();
        discountService = new SingleOrderDiscountService(testData.date, testData.orders);
    }

    @Test
    void applyWeekDayDiscount_test() {
        int discountSum = 0;
        int expectedDiscount = (testData.orders.get(Menu.CHOCOLATE_CAKE)) * DISCOUNT_UNIT;

        for (Map.Entry<Menu, Integer> order: testData.orders.entrySet()) {
            discountSum += SingleOrderDiscountService.WEEKDAY_DISCOUNT_STRATEGY
                    .apply(order);
        }

        assertThat(discountSum).isEqualTo(expectedDiscount);
    }

    @Test
    void applyWeekEndDiscount_test() {
        int discountSum = 0;
        int expectedDiscount = (testData.orders.get(Menu.BARBECUE_RIBS)) * DISCOUNT_UNIT;

        for (Map.Entry<Menu, Integer> order: testData.orders.entrySet()) {
            discountSum += SingleOrderDiscountService.WEEKEND_DISCOUNT_STRATEGY
                    .apply(order);
        }

        assertThat(discountSum).isEqualTo(expectedDiscount);
    }
}
