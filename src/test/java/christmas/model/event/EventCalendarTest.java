package christmas.model.event;

import static christmas.model.event.EventFixture.createDecemberCalendar;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import java.time.MonthDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("월의 날짜가 금요일 혹은 토요일이면 주말이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void givenFridayOrSaturDay_Then_TrueReturns(final int dayOfMonth) {
        assertThat(calendar.isWeekend(dayOfMonth)).isTrue();
    }

    @DisplayName("월의 날짜가 금요일과 토요일이 아니면 평일이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void givenDayOfMonthNotFridayAndSaturday_Then_FalseReturns(final int dayOfMonth) {
        assertThat(calendar.isWeekend(dayOfMonth)).isFalse();
    }
}
