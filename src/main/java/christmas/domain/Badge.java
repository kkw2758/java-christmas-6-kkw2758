package christmas.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Badge {
    SANTA("산타", (totalBenefitPrice) -> totalBenefitPrice >= 20_000),
    TREE("트리", (totalBenefitPrice) -> totalBenefitPrice >= 10_000 && totalBenefitPrice < 20_000),
    STAR("별", (totalBenefitPrice) -> totalBenefitPrice >= 5_000 && totalBenefitPrice < 10_000),
    NONE("없음", (totalBenefitPrice) -> totalBenefitPrice < 5_000);

    private final String name;
    private final Predicate<Integer> condition;

    Badge (String name, Predicate<Integer> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Badge from(int totalBenefitPrice) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.condition.test(totalBenefitPrice))
                .findAny()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
