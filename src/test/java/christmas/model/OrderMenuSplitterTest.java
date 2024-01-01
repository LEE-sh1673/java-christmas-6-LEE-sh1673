package christmas.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuSplitterTest {

    private final OrderMenuSplitter splitter = new OrderMenuSplitter();

    @DisplayName("메뉴 형식이 유효하지 않은 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-0", "타파스^1", " 타파스-1", "타파스-1 ", "1-타파스"})
    void givenMenus_WhenInvalidFormat_Then_ExceptionOccurs(final String menu) {
        assertThatThrownBy(() -> splitter.split(menu))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("메뉴 형식이 유효한 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1", "바비큐립-10", "초코케이크-20", "제로콜라-1"})
    void givenMenus_WhenValidFormat_Then_NoExceptionOccurs(final String menu) {
        assertThatCode(() -> splitter.split(menu))
                .doesNotThrowAnyException();
    }
}
