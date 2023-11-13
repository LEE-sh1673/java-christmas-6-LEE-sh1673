package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.model.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDayDiscountEventTest {

    private final DiscountEvent discountEvent = new SpecialDayDiscountEvent();

    @DisplayName("이벤트 달력에 별이 없는 날이면 할인 금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void givenNoSpecialDay_Then_ZeroAmountReturns(final int weekendDay) {
        final Order order = new Order(weekendDay, List.of("초코케이크-1"));
        assertThat(discountEvent.apply(order)).isEqualTo(DiscountPrice.ZERO);
    }

    @DisplayName("12월 25일에 아이스크림을 주문하면 할인 금액은 1000원이다.")
    @Test
    void givenChristmasIceCream_Then_DiscountAmountReturns() {
        // given
        final Order order = new Order(25, List.of("아이스크림-1"));

        // when
        final DiscountPrice discountPrice = discountEvent.apply(order);

        // then
        assertThat(discountPrice.getAmount()).isEqualTo(1000L);
    }
}
