package christmas.model;

import static christmas.model.OrderFixture.createOrder;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주어진 주문 메뉴를 출력한다.")
    @Test
    void givenMenus_Then_OrderMenusReturns() {
        // given
        final Order order = createOrder("25", "타파스-2,시저샐러드-1,바비큐립-1");

        // when
        final List<OrderMenu> orderMenus = order.getMenus();

        // then
        assertThat(orderMenus.size()).isEqualTo(3);

        final List<String> menuNames = orderMenus.stream()
                .map(OrderMenu::getName)
                .toList();

        assertThat(menuNames).containsExactly("타파스", "시저샐러드", "바비큐립");

        final List<Integer> quantities = orderMenus.stream()
                .map(OrderMenu::getQuantity)
                .toList();

        assertThat(quantities).containsExactly(2, 1, 1);

        final long numberOfAppetizers = order.calculateQuantity(MenuType.APPETIZER);
        final long numberOfMains = order.calculateQuantity(MenuType.MAIN);

        assertThat(numberOfAppetizers).isEqualTo(3L);
        assertThat(numberOfMains).isEqualTo(1L);
    }
}
