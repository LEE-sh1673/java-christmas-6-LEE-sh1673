package christmas.view;

import christmas.model.GiftMenu;
import christmas.model.GiftMenus;
import christmas.model.OrderMenu;
import christmas.model.event.EventBadge;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String EVENT_PLANNER_TITLE
            = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    private static final String TOTAL_EVENT_BENEFITS_TITLE
            = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    private static final String ORDER_MENUS_TITLE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRIZE_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENUS_TITLE = "<증정 메뉴>";
    private static final String BENEFITS_ITLE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRIZE_TITLE = "<총혜택 금액>";
    private static final String ESTIMATED_PAYMENT_PRIZE_TITLE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_TITLE = "<12월 이벤트 배지>";

    private static final String TOTAL_BENEFIT_PRIZE_FORMAT = "-%,d원";
    private static final String BENEFITS_FORMAT = "%s: -%,d원";
    private static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String PRICE_FORMAT = "%,d원";

    private static final String NON_EXISTS = "없음";
    private static final String NON_EXISTS_PRICE = "0원";


    // 기본 생성자가 만들어지는 것을 막는다. (인스턴스화 방지용).
    private OutputView() {
        throw new AssertionError();
    }

    public static void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printEventPlannerTitle() {
        System.out.println(EVENT_PLANNER_TITLE);
    }

    public static void printMenu(
            final int orderDay,
            final List<OrderMenu> orderMenus,
            final long totalOrderPrize
    ) {
        printMenuTitle(orderDay);
        printOrderMenus(orderMenus);
        printTotalOrderPrice(totalOrderPrize);
    }

    private static void printMenuTitle(final int orderDay) {
        System.out.printf((TOTAL_EVENT_BENEFITS_TITLE) + "%n", orderDay);
    }

    private static void printOrderMenus(final List<OrderMenu> orderMenus) {
        System.out.println(System.lineSeparator() + ORDER_MENUS_TITLE);

        for (final OrderMenu menu : orderMenus) {
            System.out.printf((ORDER_MENU_FORMAT) + "%n", menu.getName(), menu.getQuantity());
        }
    }

    private static void printTotalOrderPrice(final long totalOrderPrize) {
        System.out.println(System.lineSeparator() + TOTAL_ORDER_PRIZE_TITLE);
        System.out.printf((PRICE_FORMAT) + "%n", totalOrderPrize);
    }

    public static void printGiftMenus(final GiftMenus giftMenus) {
        final Map<GiftMenu, Long> menuDetails = giftMenus.getMenus();
        System.out.println(System.lineSeparator() + GIFT_MENUS_TITLE);

        if (menuDetails.isEmpty()) {
            System.out.println(NON_EXISTS);
            return;
        }
        for (final GiftMenu menu : menuDetails.keySet()) {
            System.out.printf((ORDER_MENU_FORMAT) + "%n", menu.getName(), menuDetails.get(menu));
        }
    }

    public static void printBenefits(
            final Map<String, Long> details,
            final long totalBenefitPrize,
            final long estimatedPaymentPrize
    ) {
        printBenefitDetails(details);
        printTotalBenefitPrize(totalBenefitPrize);
        printEstimatedPaymentPrize(estimatedPaymentPrize);
    }

    private static void printBenefitDetails(final Map<String, Long> benefits) {
        System.out.println(System.lineSeparator() + BENEFITS_ITLE);

        if (benefits.isEmpty()) {
            System.out.println(NON_EXISTS);
            return;
        }
        for (final String eventName : benefits.keySet()) {
            System.out.printf((BENEFITS_FORMAT) + "%n", eventName, benefits.get(eventName));
        }
    }

    private static void printTotalBenefitPrize(final long totalBenefitPrize) {
        System.out.println(System.lineSeparator() + TOTAL_BENEFIT_PRIZE_TITLE);

        if (totalBenefitPrize == 0L) {
            System.out.println(NON_EXISTS_PRICE);
            return;
        }
        System.out.printf((TOTAL_BENEFIT_PRIZE_FORMAT) + "%n", totalBenefitPrize);
    }

    private static void printEstimatedPaymentPrize(final long estimatedPaymentPrize) {
        System.out.println(System.lineSeparator() + ESTIMATED_PAYMENT_PRIZE_TITLE);
        System.out.printf((PRICE_FORMAT) + "%n", estimatedPaymentPrize);
    }

    public static void printEventBadge(final EventBadge badge) {
        System.out.println(System.lineSeparator() + EVENT_BADGE_TITLE);
        System.out.printf(badge.getName());
    }
}
