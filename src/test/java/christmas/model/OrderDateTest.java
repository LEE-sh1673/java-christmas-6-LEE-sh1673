package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderDateTest {

    @DisplayName("1 이상 31 이하의 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void givenDayOfMonth_WhenOutOfRange_ThenExceptionOccurs(final int dayOfMonth) {
        assertThatThrownBy(() -> OrderDate.dayOfMonth(dayOfMonth))
                .isInstanceOf(OrderException.class);
    }
}
