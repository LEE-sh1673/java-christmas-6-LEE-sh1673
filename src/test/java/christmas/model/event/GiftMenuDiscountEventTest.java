package christmas.model.event;

import static christmas.model.OrderFixture.createOrder;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Menu;
import christmas.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftMenuDiscountEventTest {

    private final DiscountEvent discountEvent = new GiftMenuDiscountEvent();

    @DisplayName("할인 전 주문 금액이 12만원 미만이면 할인 금액은 0원이다.")
    @Test
    void givenOrderPrice_LessThan_120000_Then_ZeroAmountReturns() {
        // given
        final Order order = createOrder("26", "티본스테이크-1,바비큐립-1");

        // when
        final DiscountPrice discountPrice = discountEvent.apply(order);

        // when
        assertThat(discountPrice.isZero()).isTrue();
    }

    @DisplayName("할인 전 주문 금액이 12만원 이상이면 할인 금액은 샴페인 가격이다.")
    @Test
    void givenOrderPrice_GreaterThan_120000_Then_ChampagneReturns() {
        // given
        final Order order = createOrder("26",
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"
        );

        // when
        final DiscountPrice discountPrice = discountEvent.apply(order);

        // when
        assertThat(discountPrice.getAmount()).isEqualTo(Menu.CHAMPAGNE.getPrize());
    }
}
