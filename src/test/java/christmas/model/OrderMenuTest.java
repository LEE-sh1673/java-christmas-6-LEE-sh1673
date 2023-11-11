package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("주문 메뉴의 가격을 구한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "티본스테이크:1:55000", "바비큐립:2:108000",
            "초코케이크:2:30000", "제로콜라:1:3000"}, delimiter = ':')
    void givenOrderMenu_Then_priceReturns(
            final String menuName,
            final int quantity,
            final long price
    ) {
        final OrderMenu orderMenu = new OrderMenu(menuName, quantity);
        assertThat(orderMenu.getPrize()).isEqualTo(price);
    }
}
