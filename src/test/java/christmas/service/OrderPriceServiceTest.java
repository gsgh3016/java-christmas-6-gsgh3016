package christmas.service;

import christmas.model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderPriceServiceTest {
    private ServiceTestData testData;
    private OrderPriceService priceService;

    @BeforeEach
    void setUp() {
        testData = new ServiceTestData();
        priceService = new OrderPriceService(testData.orders);
    }

    @Test
    void getTotalPrice_test() {
        int expectedPrice = 0;
        for (Map.Entry<Menu, Integer> order: testData.orders.entrySet()) {
            expectedPrice += order.getKey().getPrice() * order.getValue();
        }

        assertThat(priceService.getTotalPrice())
                .isEqualTo(expectedPrice);
    }
}
