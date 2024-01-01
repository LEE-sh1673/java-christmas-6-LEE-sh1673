package christmas.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenusFactoryTest {

    private final OrderMenuSplitter splitter = new OrderMenuSplitter();
    private final OrderMenusFactory factory = new OrderMenusFactory(splitter);

    @DisplayName("중복 메뉴를 입력한 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1, 타파스-2", "바비큐립-1,초코케이크-2,제로콜라-1, 초코케이크-2"})
    void givenMenus_WhenDuplicates_ThenExceptionOccurs(final String menus) {
        assertThatThrownBy(() -> factory.createMenus(menus))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("중복 메뉴가 없는 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-2,레드와인-1,초코케이크-1",
            "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"
    })
    void givenMenus_WhenDistinct_ThenNoExceptionOccurs(final String menus) {
        assertThatCode(() -> factory.createMenus(menus))
                .doesNotThrowAnyException();
    }
}
