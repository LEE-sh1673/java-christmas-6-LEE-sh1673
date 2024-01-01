package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void givenMenus_WhenAllBeverage_ThenExceptionOccurs() {
        assertThatThrownBy(
                () -> new OrderMenus(List.of(
                        new OrderMenu("제로콜라", 2),
                        new OrderMenu("레드와인", 10)
                )))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("주문 메뉴의 수량이 20개를 초과하면 예외가 발생한다.")
    @Test
    void givenTotalQuantity_WhenOutOfRange_ThenExceptionOccurs() {
        assertThatThrownBy(
                () -> new OrderMenus(List.of(
                        new OrderMenu("바비큐립", 4),
                        new OrderMenu("초코케이크", 2),
                        new OrderMenu("제로콜라", 11),
                        new OrderMenu("초코케이크", 4)
                )))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("주문 메뉴에서 총 음료수의 수량을 구할 수 있다.")
    @Test
    void testCountFor() {
        final OrderMenus menus = new OrderMenus(List.of(
                new OrderMenu("레드와인", 3),
                new OrderMenu("바비큐립", 4),
                new OrderMenu("초코케이크", 2),
                new OrderMenu("제로콜라", 3),
                new OrderMenu("초코케이크", 4)
        ));
        final Quantity beverageTotal = menus.count(MenuCategory.BEVERAGE);

        assertThat(beverageTotal.getAmount()).isEqualTo(6);
    }
}
