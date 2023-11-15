package christmas.util;

import christmas.model.DiscountManager;
import christmas.util.constant.Category;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountManagerTest {
    @Test
    void add_test() {
        DiscountManager.add(Category.DISCOUNT, "크리스마스 디데이 할인: -1,200원");
        DiscountManager.add(Category.DISCOUNT, "평일 할인: -4,046원");

        assertThat(DiscountManager.findByCategory(Category.DISCOUNT))
                .contains("크리스마스 디데이 할인: -1,200원")
                .contains("평일 할인: -4,046원");
    }
}
