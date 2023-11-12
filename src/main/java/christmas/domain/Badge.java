package christmas.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Badge {
    SANTA("산타", (totalBenefitAmount) -> totalBenefitAmount >= 20000),
    TREE("트리", (totalBenefitAmount) -> totalBenefitAmount >= 10000 && totalBenefitAmount < 20000),
    STAR("별", (totalBenefitAmount) -> totalBenefitAmount >= 5000 && totalBenefitAmount < 10000),
    NONE("없음", (totalBenefitAmount) -> totalBenefitAmount < 5000);

    private final String name;
    private final Predicate<Integer> condition;

    Badge(String name, Predicate<Integer> condition) {
        this.name = name;
        this.condition = condition;
    }
    public static Badge findBadge(int totalBenefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(ranking -> ranking.condition.test(totalBenefitAmount))
                .findAny()
                .orElse(NONE);
    }
}
