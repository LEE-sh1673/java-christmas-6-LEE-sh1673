package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {

    @DisplayName("메뉴의 수량이 1 이상의 숫자가 아닌 경우 예외가 발생한다.")
    @Test
    void givenQuantityLessThanOne_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> new OrderMenu("티본스테이크", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_ORDER.getMessage());
    }

    @DisplayName("메뉴의 수량이 20개를 초과하는 경우 예외가 발생한다.")
    @Test
    void givenQuantityExceeded_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> new OrderMenu("티본스테이크", 21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.MAX_ORDER_EXCEEDED.getMessage());
    }
}
