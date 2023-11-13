package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.GiftMenus;
import christmas.model.Order;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBenefitPlannerTest {

    private static final EventBenefitPlanner planner = new EventBenefitPlanner();

    @BeforeAll
    static void setUp() {
        planner.add(new DdayDiscountEvent());
        planner.add(new WeekdayDiscountEvent());
        planner.add(new WeekendDiscountEvent());
        planner.add(new SpecialDayDiscountEvent());
        planner.add(new GiftMenuDiscountEvent());
    }

    @DisplayName("총 주문 금액이 10,000원 미만일 때 이벤트를 적용하지 않는다.")
    @Test
    void givenOrderPrice_LessThan_10000_Then_NoEventApplied() {
        // given
        final Order order = new Order(25, List.of("아이스크림-1", "제로콜라-1"));

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
        final Order order = new Order(26, List.of("제로콜라-1", "티본스테이크-1"));

        // when
        final EventBenefits benefits = planner.plan(order);

        // then
        assertThat(benefits.calculateTotalPrize()).isEqualTo(0L);
    }

    @DisplayName("주어진 메뉴를 주문했을 때 총 혜택 금액을 구한다.")
    @Test
    void givenOrderMenus_Then_TotalBenefitPrizeReturns() {
        // given
        final Order order = new Order(3,
                List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1")
        );

        // when
        final EventBenefits benefits = planner.plan(order);

        // then
        assertThat(benefits.calculateTotalPrize()).isEqualTo(31246L);
    }

    @DisplayName("크리스마스에 아이스크림 2개를 주문하면 예상 결제 금액은 1,554원이다.")
    @Test
    void givenChristmasWithIceCream_Then_BenefitsReturn() {
        // given
        final Order order = new Order(25, List.of("아이스크림-2"));
        final GiftMenus giftMenus = GiftMenus.from(order);

        // when
        final EventBenefits benefits = planner.plan(order);

        // then
        final long totalBenefitPrize = benefits.calculateTotalPrize();
        final long estimatedPaymentPrize
                = benefits.calculateEstimatedPaymentPrize(order, giftMenus);

        assertThat(totalBenefitPrize).isEqualTo(8446L);
        assertThat(estimatedPaymentPrize).isEqualTo(1554L);
    }

    @DisplayName("할인 금액이 없으면 예상 결제 금액은 할인 전 총주문 금액이다.")
    @Test
    void givenZeroDiscountAmount_Then_OrderPrizeReturns() {
        // given
        final Order order = new Order(26, List.of("제로콜라-1", "티본스테이크-1"));
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
        final Order order = new Order(3,
                List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1")
        );
        final GiftMenus giftMenus = GiftMenus.from(order);

        // when
        final EventBenefits benefits = planner.plan(order);
        final long estimatedPaymentPrize
                = benefits.calculateEstimatedPaymentPrize(order, giftMenus);

        // then
        assertThat(estimatedPaymentPrize).isEqualTo(135754L);
    }

    @DisplayName("주어진 메뉴를 주문했을 때 발급되는 배지를 구한다.")
    @Test
    void givenOrderMenus_Then_EventBadgeReturns() {
        // given
        final Order order = new Order(3,
                List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1")
        );

        // when
        final EventBenefits benefits = planner.plan(order);

        // then
        assertThat(benefits.publishBadge()).isEqualTo(EventBadge.SANTA);
    }
}
