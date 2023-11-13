package christmas.model;

import static christmas.model.OrderFixture.createMenus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorType;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrderMenusTest {

    @DisplayName("중복된 메뉴를 입력하는 경우 예외가 발생한다.")
    @Test
    void givenMenuDuplicated_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> createMenus("시저샐러드-1", "시저샐러드-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_ORDER.getMessage());
    }

    @DisplayName("중복되지 않은 메뉴를 입력하는 경우 예외가 발생하지 않는다.")
    @Test
    void givenMenuNonDuplicated_Then_NoExceptionOccurs() {
        assertThatCode(() -> createMenus(
                "바비큐립-3", "시저샐러드-4", "초코케이크-8", "제로콜라-5"))
                .doesNotThrowAnyException();
    }

    @DisplayName("전체 메뉴의 개수가 20개를 초과하는 경우 예외가 발생한다.")
    @Test
    void givenMenuSizeExceeded_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> createMenus(
                "바비큐립-3", "시저샐러드-4", "초코케이크-8", "제로콜라-6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.MAX_ORDER_EXCEEDED.getMessage());
    }

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void givenOnlyBeverage_Then_ExceptionOccurs() {
        assertThatThrownBy(() ->
                createMenus("제로콜라-3", "레드와인-4", "샴페인-10"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.ONLY_BEVERAGE_ORDERED.getMessage());
    }

    @DisplayName("총 주문 금액을 구한다.")
    @ParameterizedTest
    @MethodSource("generateMenuNamesWithTotalPrice")
    void givenOrderMenus_Then_TotalPriceReturns(
            final String[] menuNames,
            final long totalPrize
    ) {
        final OrderMenus orderMenus = createMenus(menuNames);
        assertThat(orderMenus.calculateTotalPrize()).isEqualTo(totalPrize);
    }

    private static Stream<Arguments> generateMenuNamesWithTotalPrice() {
        return Stream.of(
                Arguments.of(
                        new String[]{"바비큐립-1", "티본스테이크-1", "초코케이크-2", "제로콜라-1"},
                        142000L
                ),
                Arguments.of(
                        new String[]{"타파스-1", "제로콜라-1"},
                        8500L
                )
        );
    }
}
