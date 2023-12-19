package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("존재하지 않는 메뉴인 경우 예외가 발생한다.")
    @Test
    void givenMenuName_WhenNonExists_ExceptionOccurs() {
        assertThatThrownBy(() -> Menu.findByName("쿨피스"))
                .isInstanceOf(OrderException.class);
    }
}
