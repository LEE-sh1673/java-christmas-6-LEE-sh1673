package christmas.model.event;


import static christmas.model.OrderFixture.createOrder;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DdayDiscountEventTest {

    private final DiscountEvent discountEvent = new DdayDiscountEvent();

    @DisplayName("주문 날짜가 26일 이상이면 할인 금액은 0원이다.")
    @Test
    void givenDayExceeded_Then_zeroAmountReturns() {
        // given
        final Order order = createOrder("27", "타파스-1");

        // when
        final DiscountPrice discountPrice = discountEvent.apply(order);

        // then
        assertThat(discountPrice.isZero()).isTrue();
    }

    @DisplayName("디데이 할인을 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {"3:1200", "25:3400"}, delimiter = ':')
    void givenDayInRage_Then_DiscountAmountReturns(
            final String day,
            final long discountAmount
    ) {
        // given
        final Order order = createOrder(day, "타파스-1");

        // when
        final DiscountPrice discountPrice = discountEvent.apply(order);

        // then
        assertThat(discountPrice.getAmount()).isEqualTo(discountAmount);
    }
}
