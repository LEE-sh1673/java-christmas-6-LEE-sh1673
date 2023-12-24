package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {

    private static OrderMenus createMenus(final String menu) {
        return new OrderMenus(OrderMenusFactory.createMenus(menu));
    }

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void givenMenus_WhenAllBeverage_ThenExceptionOccurs() {
        assertThatThrownBy(() -> createMenus("제로콜라-2,레드와인-10"))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("주문 메뉴의 수량이 20개를 초과하면 예외가 발생한다.")
    @Test
    void givenTotalQuantity_WhenOutOfRange_ThenExceptionOccurs() {
        assertThatThrownBy(() -> createMenus("바비큐립-4,초코케이크-2,제로콜라-11,초코케이크-4"))
                .isInstanceOf(OrderException.class);
    }
}
