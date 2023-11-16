package christmas.model.event;

public class DiscountPrice {

    private static final long ZERO_AMOUNT = 0L;
    public static final DiscountPrice ZERO = new DiscountPrice(ZERO_AMOUNT);

    private final long amount;

    private DiscountPrice(final long amount) {
        this.amount = amount;
    }

    public static DiscountPrice of(final long amount) {
        return new DiscountPrice(amount);
    }

    public boolean isZero() {
        return amount == ZERO_AMOUNT;
    }

    public long getAmount() {
        return amount;
    }
}
