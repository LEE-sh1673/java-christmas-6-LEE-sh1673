package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("메뉴판에 없는 메뉴를 입력하는 경우 예외가 발생한다.")
    @Test
    void givenNonExistMenu_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> Menu.findMenu("황금올리브"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_ORDER.getMessage());
    }
}
