package christmas.model.event;

public class DiscountPrice {

    public static final DiscountPrice ZERO = new DiscountPrice(0L);

    private final long amount;

    private DiscountPrice(final long amount) {
        this.amount = amount;
    }

    public static DiscountPrice of(final long amount) {
        return new DiscountPrice(amount);
    }

    public boolean isZero() {
        return amount == 0L;
    }

    public long getAmount() {
        return amount;
    }
}
