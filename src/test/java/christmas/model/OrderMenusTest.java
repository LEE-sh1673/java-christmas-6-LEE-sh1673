package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.OrderException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {


    //TODO: 음료 주문, 메뉴의 총 수량에 대한 검증 테스트 추가할 것
    @DisplayName("음료만 주문하는 경우 예외가 발생한다.")
    @Test
    void givenMenus_WhenAllBeverage_ThenExceptionOccurs() {
        List<OrderMenu> menus = OrderMenusFactory.createMenus("제로콜라-2,레드와인-10");

        assertThatThrownBy(() -> new OrderMenus(menus))
                .isInstanceOf(OrderException.class);
    }
}
