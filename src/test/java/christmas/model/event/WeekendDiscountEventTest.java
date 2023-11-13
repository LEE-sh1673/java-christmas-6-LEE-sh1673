package christmas.model.event;

import static christmas.model.OrderFixture.createOrder;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekendDiscountEventTest {

    private final DiscountEvent discountEvent = new WeekendDiscountEvent();

    @DisplayName("주문 일자가 평일인 경우 할인 금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "10", "11", "12", "13", "14", "28", "31"})
    void givenDayWeekDay_Then_ZeroAmountReturns(final String weekendDay) {
        final Order order = createOrder(weekendDay, List.of("티본스테이크-1"));
        assertThat(discountEvent.apply(order)).isEqualTo(DiscountPrice.ZERO);
    }

    @DisplayName("주문 일자가 주말이고 메인 메뉴가 없는 경우 할인 금액은 0원이다.")
    @Test
    void givenDayWeekendNoMain_Then_ZeroAmountReturns() {
        final Order order = createOrder("1", List.of("초코케이크-1"));
        assertThat(discountEvent.apply(order)).isEqualTo(DiscountPrice.ZERO);
    }

    @DisplayName("주문 일자가 주말이고 메인 메뉴가 주여졌을 때 할인 금액을 구한다.")
    @Test
    void givenDayWeekendWithMain_Then_DiscountAmountReturns() {
        // given
        final Order order = createOrder("1", List.of("티본스테이크-1", "크리스마스파스타-2"));

        // when
        final DiscountPrice discountPrice = discountEvent.apply(order);

        // then
        assertThat(discountPrice.getAmount()).isEqualTo(6069L);
    }
}
