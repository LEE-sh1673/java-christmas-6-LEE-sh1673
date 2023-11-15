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
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenusTest {

    @DisplayName("메뉴 앞 혹은 뒤에 콤마(,)가 존재하는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "시저샐러드-1,크리스마스파스타-1,", ",아이스크림-2", ",초코케이크-8,제로콜라-5,,"
    })
    void givenMenuEndsWithComma_Then_ExceptionOccurs(final String namesWithQuantity) {
        assertThatThrownBy(() -> createMenus(namesWithQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_ORDER.getMessage());
    }

    @DisplayName("중복된 메뉴를 입력하는 경우 예외가 발생한다.")
    @Test
    void givenMenuDuplicated_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> createMenus("시저샐러드-1,시저샐러드-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.INVALID_ORDER.getMessage());
    }

    @DisplayName("중복되지 않은 메뉴를 입력하는 경우 예외가 발생하지 않는다.")
    @Test
    void givenMenuNonDuplicated_Then_NoExceptionOccurs() {
        assertThatCode(() -> createMenus(
                "바비큐립-3,시저샐러드-4,초코케이크-8,제로콜라-5"))
                .doesNotThrowAnyException();
    }

    @DisplayName("전체 메뉴의 개수가 20개를 초과하는 경우 예외가 발생한다.")
    @Test
    void givenMenuSizeExceeded_Then_ExceptionOccurs() {
        assertThatThrownBy(() -> createMenus(
                "바비큐립-3,시저샐러드-4,초코케이크-8,제로콜라-6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.MAX_ORDER_EXCEEDED.getMessage());
    }

    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void givenOnlyBeverage_Then_ExceptionOccurs() {
        assertThatThrownBy(() ->
                createMenus("제로콜라-3,레드와인-4,샴페인-10"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorType.ONLY_BEVERAGE_ORDERED.getMessage());
    }

    @DisplayName("총 주문 금액을 구한다.")
    @ParameterizedTest
    @MethodSource("generateMenuNamesWithTotalPrice")
    void givenOrderMenus_Then_TotalPriceReturns(
            final String menuNames,
            final long totalPrize
    ) {
        final OrderMenus orderMenus = createMenus(menuNames);
        assertThat(orderMenus.calculateTotalPrize()).isEqualTo(totalPrize);
    }

    private static Stream<Arguments> generateMenuNamesWithTotalPrice() {
        return Stream.of(
                Arguments.of(
                        "바비큐립-1,티본스테이크-1,초코케이크-2,제로콜라-1",
                        142000L
                ),
                Arguments.of(
                        "타파스-1,제로콜라-1",
                        8500L
                )
        );
    }

    @DisplayName("주문 메뉴에서 주어진 메뉴 유형에 대한 총 수량을 구한다.")
    @ParameterizedTest
    @MethodSource("generateMenusWithTypeAndCount")
    void givenOrderMenus_Then_TotalMenuQuantityReturns(
            final MenuType menuType,
            final int expectedMenuQuantity
    ) {
        // given
        final OrderMenus orderMenus = createMenus(
                "티본스테이크-1,시저샐러드-4,바비큐립-1,초코케이크-2,제로콜라-1"
        );

        // when
        final int dessertCount = orderMenus.countQuantity(menuType);

        // then
        assertThat(dessertCount).isEqualTo(expectedMenuQuantity);
    }

    private static Stream<Arguments> generateMenusWithTypeAndCount() {
        return Stream.of(
                Arguments.of(MenuType.DESSERT, 2),
                Arguments.of(MenuType.MAIN, 2),
                Arguments.of(MenuType.BEVERAGE, 1),
                Arguments.of(MenuType.APPETIZER, 4)
        );
    }
}
