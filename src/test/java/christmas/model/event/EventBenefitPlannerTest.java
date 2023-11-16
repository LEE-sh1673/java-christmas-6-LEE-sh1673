package christmas.model.event;

import static christmas.model.OrderFixture.createOrder;
import static christmas.model.event.EventFixture.createDecemberPlanner;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.GiftMenus;
import christmas.model.Order;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class EventBenefitPlannerTest {

    private static final EventBenefitPlanner planner = createDecemberPlanner();

    private static final Order BASIC_ORDER = createOrder(
            "3",
            "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"
    );

    @DisplayName("총 주문 금액이 10,000원 미만일 때 이벤트를 적용하지 않는다.")
    @Test
    void givenOrderPrice_LessThan_10000_Then_NoEventApplied() {
        // given
        final Order order = createOrder("25", "아이스크림-1,제로콜라-1");

        // when
        final EventBenefits benefits = planner.plan(order);
        final Map<String, Long> benefitDetails = benefits.getBenefits();

        // then
        assertThat(benefitDetails.isEmpty()).isTrue();
    }

    @DisplayName("적용된 이벤트가 없으면 총 혜택 금액은 0원이다.")
    @Test
    void givenOrderMenus_NoEventApplied_Then_ZeroAmountReturns() {
        // given
        final Order order = createOrder("26", "제로콜라-1,티본스테이크-1");

        // when
        final EventBenefits benefits = planner.plan(order);

        // then
        assertThat(benefits.calculateTotalPrize()).isEqualTo(0L);
    }

    @DisplayName("주어진 메뉴를 주문했을 때 총 혜택 금액을 구한다.")
    @Test
    void givenOrderMenus_Then_TotalBenefitPrizeReturns() {
        final EventBenefits benefits = planner.plan(BASIC_ORDER);
        assertThat(benefits.calculateTotalPrize()).isEqualTo(31246L);
    }

    @DisplayName("할인 금액이 없으면 예상 결제 금액은 할인 전 총주문 금액이다.")
    @Test
    void givenZeroDiscountAmount_Then_OrderPrizeReturns() {
        // given
        final Order order = createOrder("26", "제로콜라-1,티본스테이크-1");
        final GiftMenus giftMenus = GiftMenus.from(order);

        // when
        final EventBenefits benefits = planner.plan(order);
        final long estimatedPaymentPrize
                = benefits.calculateEstimatedPaymentPrize(order, giftMenus);

        // then
        assertThat(estimatedPaymentPrize).isEqualTo(order.calculateTotalPrize());
    }

    @DisplayName("주어진 메뉴를 주문했을 때 총 할인 금액을 구한다.")
    @Test
    void givenOrderMenus_Then_EstimatedPaymentPrizeReturns() {
        // given
        final GiftMenus giftMenus = GiftMenus.from(BASIC_ORDER);

        // when
        final EventBenefits benefits = planner.plan(BASIC_ORDER);
        final long estimatedPaymentPrize
                = benefits.calculateEstimatedPaymentPrize(BASIC_ORDER, giftMenus);

        // then
        assertThat(estimatedPaymentPrize).isEqualTo(135754L);
    }

    @DisplayName("주어진 메뉴를 주문했을 때 발급되는 배지를 구한다.")
    @Test
    void givenOrderMenus_Then_EventBadgeReturns() {
        final EventBenefits benefits = planner.plan(BASIC_ORDER);
        assertThat(benefits.publishBadge()).isEqualTo(EventBadge.SANTA);
    }

    @DisplayName("주어진 메뉴를 주문했을 때 총 할인 금액, 예상 결제 금액, 이벤트 배지 이름을 구한다.")
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/order.csv")
    void givenOrderMenus_Then_AllBenefitDetailsReturns(
            final String day,
            final String menus,
            final long expectedBenefitPrize,
            final long expectedPaymentPrize,
            final String expectedBadgeName
    ) {
        // given
        final Order order = createOrder(day, menus);
        final GiftMenus giftMenus = GiftMenus.from(order);

        // when
        final EventBenefits benefits = planner.plan(order);

        final long totalBenefitPrize = benefits.calculateTotalPrize();
        final long estimatedPaymentPrize
                = benefits.calculateEstimatedPaymentPrize(order, giftMenus);
        final EventBadge badge = benefits.publishBadge();

        // then
        assertThat(totalBenefitPrize).isEqualTo(expectedBenefitPrize);
        assertThat(estimatedPaymentPrize).isEqualTo(expectedPaymentPrize);
        assertThat(badge.getName()).isEqualTo(expectedBadgeName);
    }
}
