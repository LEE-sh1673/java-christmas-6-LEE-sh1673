package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderDateTest {

    @DisplayName("1 이상 31 이하의 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void givenDayOutOfRange_Then_ExceptionOccurs(final String day) {
        assertThatThrownBy(() -> OrderDate.withDay(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_DATE.getMessage());
    }
}
