package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.exception.ErrorType;
import christmas.model.Menu;
import java.time.Month;
import java.time.Year;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventCalendarTest {

    private final EventCalendar calendar
            = EventCalendar.of(Year.of(2023), Month.DECEMBER);

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
