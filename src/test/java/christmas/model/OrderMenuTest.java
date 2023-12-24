package christmas.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {

    @DisplayName("주문 메뉴의 수량이 1 미만인 경우 예외가 발생한다.")
    @Test
    void givenOrderQuantity_WhenLessThanOne_ThenExceptionOccurs() {
        assertThatThrownBy(() -> new OrderMenu("제로콜라", 0))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("주문 메뉴의 수량이 1 이상인 경우 예외가 발생하지 않는다.")
    @Test
    void givenOrderQuantity_WhenGreaterOrEqualToOne_ThenNoExceptionOccurs() {
        assertThatCode(() -> new OrderMenu("제로콜라", 1))
                .doesNotThrowAnyException();
    }
}
