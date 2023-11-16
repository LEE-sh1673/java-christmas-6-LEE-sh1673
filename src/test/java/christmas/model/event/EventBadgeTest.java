package christmas.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventBadgeTest {

    @DisplayName("총 혜택 금액이 5천원 미만이면 '없음'을 부여한다.")
    @ParameterizedTest
    @ValueSource(longs = {-1L, 0L, 4999L})
    void givenBenefitPrize_LessThan_5000_Then_NONE(final long benefitPrize) {
        assertThat(EventBadge.find(benefitPrize)).isEqualTo(EventBadge.NONE);
    }

    @DisplayName("총 혜택 금액이 5천원 이상, 1만원 미만이면 '별'을 부여한다.")
    @ParameterizedTest
    @ValueSource(longs = {5000L, 9999L})
    void givenBenefitPrize_GreaterThan_5000_LessThan_10000_Then_STAR(final long benefitPrize) {
        assertThat(EventBadge.find(benefitPrize)).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("총 혜택 금액이 1만원 이상, 2만원 미만이면 '트리'를 부여한다.")
    @ParameterizedTest
    @ValueSource(longs = {10000L, 19999L})
    void givenBenefitPrize_GreaterThan_10000_LessThan_20000_Then_TREE(final long benefitPrize) {
        assertThat(EventBadge.find(benefitPrize)).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("총 혜택 금액이 2만원 이상이면 '산타'를 부여한다.")
    @Test
    void givenBenefitPrize_GreaterThan_20000_Then_SANTA() {
        assertThat(EventBadge.find(20000L)).isEqualTo(EventBadge.SANTA);
    }
}
