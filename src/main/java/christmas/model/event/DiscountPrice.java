package christmas.model.event;

import java.util.Objects;

public class DiscountPrice {

    public static final DiscountPrice ZERO = new DiscountPrice(0L);

    private final long amount;

    private DiscountPrice(final long amount) {
        this.amount = amount;
    }

    public static DiscountPrice of(final long amount) {
        return new DiscountPrice(amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DiscountPrice other = (DiscountPrice) o;
        return amount == other.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public long getAmount() {
        return amount;
    }
}
