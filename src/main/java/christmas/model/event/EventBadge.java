package christmas.model.event;

import java.util.Arrays;
import java.util.function.Predicate;

public enum EventBadge {

    NONE("없음", (benefitPrize) -> benefitPrize < 5000L),
    STAR("별", (benefitPrize) -> benefitPrize >= 5000L && benefitPrize < 10000L),
    TREE("트리", (benefitPrize) -> benefitPrize >= 10000L && benefitPrize < 20000L),
    SANTA("산타", (benefitPrize) -> benefitPrize >= 20000L);

    private final String name;

    private final Predicate<Long> expression;

    EventBadge(final String name, final Predicate<Long> expression) {
        this.name = name;
        this.expression = expression;
    }

    public static EventBadge find(final long benefitPrize) {
        return Arrays.stream(values())
                .filter(badge -> badge.check(benefitPrize))
                .findAny()
                .orElse(NONE);
    }

    public boolean check(final long benefitPrize) {
        return expression.test(benefitPrize);
    }

    public String getName() {
        return name;
    }
}
