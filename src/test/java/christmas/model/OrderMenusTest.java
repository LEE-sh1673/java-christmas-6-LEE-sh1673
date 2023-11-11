package christmas.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {

    @DisplayName("중복된 메뉴를 입력하는 경우 예외가 발생한다.")
    @Test
    void givenMenuDuplicated_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> new OrderMenus(List.of("시저샐러드-1", "시저샐러드-1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_ORDER.getMessage());
    }

    @DisplayName("중복되지 않은 메뉴를 입력하는 경우 예외가 발생하지 않는다.")
    @Test
    void givenMenuNonDuplicated_Then_NoExceptionOccurs() {
        assertThatCode(() -> new OrderMenus(List.of("티본스테이크-1", "시저샐러드-1")))
                .doesNotThrowAnyException();
    }
}
