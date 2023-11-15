package christmas.util.constant;

import java.util.ArrayList;
import java.util.List;

public class Formatting {
    public static final String MENU = "%s %d개";
    public static final String PRICE = "%,d원";
    public static final String DISCOUNT = "%s: %,d원";

    public static final List<String> DISCOUNT_ORDERING =
            List.of(
                    Category.GIFT,
                    Category.DISCOUNT,
                    Category.DISCOUNT_PRICE,
                    Category.TOTAL_PRICE_AFTER_DISCOUNT,
                    Category.BADGE
            );
}
