package christmas.model;

import static christmas.model.OrderFixture.createOrder;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftMenusTest {

    @DisplayName("할인 전 주문 금액이 10,000원 미만일 경우 증정 메뉴는 \"없음\"이다.")
    @Test
    void givenOrderPrice_LessThan_10000_Then_NoGiftMenuReturns() {
        // given
        final Order order = createOrder("26", "타파스-1,제로콜라-1");

        // when
        final GiftMenus giftMenus = GiftMenus.from(order);
        final Map<GiftMenu, Long> giftMenuDetails = giftMenus.getMenus();

        // then
        assertThat(giftMenuDetails.isEmpty()).isTrue();
    }

    @DisplayName("할인 전 주문 금액이 12만원 이상이면 샴페인 1개를 증정한다.")
    @Test
    void givenOrderPrice_GreaterThan_120000_Then_ChampagneReturns() {
        // given
        final Order order = createOrder("26",
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"
        );

        // when
        final GiftMenus giftMenus = GiftMenus.from(order);
        final Map<GiftMenu, Long> menus = giftMenus.getMenus();

        // when
        assertThat(menus.size()).isEqualTo(1);

        final GiftMenu giftMenu = menus.keySet()
                .stream()
                .toList()
                .get(0);

        assertThat(giftMenu).isEqualTo(GiftMenu.CHAMPAGNE);
        assertThat(giftMenu.getName()).isEqualTo(Menu.CHAMPAGNE.getName());
        assertThat(giftMenu.getPrize()).isEqualTo(Menu.CHAMPAGNE.getPrize());
        assertThat(menus.get(giftMenu)).isEqualTo(1L);
    }
}
