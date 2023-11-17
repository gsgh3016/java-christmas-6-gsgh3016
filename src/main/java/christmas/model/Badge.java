package christmas.model;

import java.util.function.Predicate;

public enum Badge {
    NONE("없음", discount ->
            discount < 5_000
    ),
    STAR("별", discount ->
            5_000 <= discount && discount < 10_000
    ),
    TREE("트리", discount ->
            10_000 <= discount && discount < 20_000
    ),
    SANTA("산타", discount ->
            discount >= 20_000
    );

    private String name;
    private Predicate<Integer> condition;

    Badge(String name, Predicate<Integer> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Badge select(int discount) {
        for (Badge badge : Badge.values()) {
            if (badge.condition.test(discount)) {
                return badge;
            }
        }
        return NONE;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
