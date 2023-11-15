package christmas.model.event;

import static christmas.model.event.EventFixture.createDecemberCalendar;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventCalendarTest {

    private final EventCalendar calendar = createDecemberCalendar();

    @DisplayName("월의 날짜가 해당 월 내에 포함되지 않은 숫자이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void givenDayOfMonth_OutOfRange_Then_ExceptionOccurs(final int dayOfMonth) {
        assertThatThrownBy(() -> calendar.isWeekend(dayOfMonth))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_DATE.getMessage());

        assertThatThrownBy(() -> calendar.isSpecialDay(dayOfMonth))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_DATE.getMessage());
    }
}
