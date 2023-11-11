package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenusTest {

    @DisplayName("메뉴의 형식이 '메뉴-개수' 형식이 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"티본=1", "티본 비본-1", "  -1", "    타파스-20", "티본스테이크-20   "})
    void givenInvalidMenuForm_Then_ExceptionOccurs(final String menuNames) {
        assertThatThrownBy(() -> new OrderMenus(List.of(menuNames)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_ORDER.getMessage());
    }
}
