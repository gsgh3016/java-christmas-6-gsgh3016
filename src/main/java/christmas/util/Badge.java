package christmas.util;

import java.util.function.Predicate;

public enum Badge {
    NONE(null, totalPrice ->
            totalPrice < 5_000
    ),
    STAR("별", totalPrice ->
            5_000 <= totalPrice && totalPrice < 10_000
    ),
    TREE("트리", totalPrice ->
            10_000 <= totalPrice && totalPrice < 20_000
    ),
    SANTA("산타", totalPrice ->
            totalPrice >= 20_000
    );

    private String name;
    private Predicate<Integer> condition;

    Badge(String name, Predicate<Integer> condition) {
        this.name = name;
        this.condition = condition;
    }
}
