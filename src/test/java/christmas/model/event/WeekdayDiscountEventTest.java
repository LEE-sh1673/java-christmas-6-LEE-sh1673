package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekdayDiscountEventTest {

    private final DiscountEvent discountEvent = new WeekdayDiscountEvent();

    @DisplayName("주문 일자가 금요일 또는 토요일인 경우 할인 금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void givenDayWeekend_Then_ZeroAmountReturns(final int weekendDay) {
        final Order order = new Order(weekendDay, List.of("초코케이크-1"));
        assertThat(discountEvent.apply(order)).isEqualTo(DiscountPrice.ZERO);
    }

    @DisplayName("주문 일자가 평일이고 디저트 메뉴가 없는 경우 할인 금액은 0원이다.")
    @Test
    void givenDayWeekdayNoDessert_Then_ZeroAmountReturns() {
        final Order order = new Order(3, List.of("티본스테이크-1"));
        assertThat(discountEvent.apply(order)).isEqualTo(DiscountPrice.ZERO);
    }

    @DisplayName("주문 일자가 평일이고 디저트 메뉴가 주여졌을 때 할인 금액을 구한다.")
    @Test
    void givenDayWeekDayWithDessert_Then_DiscountAmountReturns() {
        // given
        final Order order = new Order(3, List.of("초코케이크-2", "아이스크림-1"));

        // when
        final DiscountPrice discountPrice = discountEvent.apply(order);

        // then
        assertThat(discountPrice.getAmount()).isEqualTo(6069L);
    }

}
