package christmas.view;

import christmas.model.GiftMenu;
import christmas.model.GiftMenus;
import christmas.model.Order;
import christmas.model.OrderMenu;
import christmas.model.event.EventBadge;
import christmas.model.event.EventBenefits;
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
            final Order order,
            final GiftMenus giftMenus,
            final EventBenefits benefits
    ) {
        printMenuTitle(order);
        printOrderMenus(order);
        printTotalOrderPrice(order);

        printGiftMenus(giftMenus.getMenus());
        printBenefits(benefits.getBenefits());

        printTotalBenefitPrize(benefits);
        printEstimatedPaymentPrize(benefits, order, giftMenus);
        printEventBadge(benefits);
    }

    private static void printMenuTitle(final Order order) {
        System.out.printf((TOTAL_EVENT_BENEFITS_TITLE) + "%n", order.getDay());
    }

    private static void printOrderMenus(final Order order) {
        System.out.println(System.lineSeparator() + ORDER_MENUS_TITLE);

        for (final OrderMenu menu : order.getMenus()) {
            System.out.printf((ORDER_MENU_FORMAT) + "%n", menu.getName(), menu.getQuantity());
        }
    }

    private static void printTotalOrderPrice(final Order order) {
        System.out.println(System.lineSeparator() + TOTAL_ORDER_PRIZE_TITLE);
        System.out.printf((PRICE_FORMAT) + "%n", order.calculateTotalPrize());
    }

    private static void printGiftMenus(final Map<GiftMenu, Long> giftMenus) {
        System.out.println(System.lineSeparator() + GIFT_MENUS_TITLE);

        if (giftMenus.isEmpty()) {
            System.out.println(NON_EXISTS);
            return;
        }
        for (final GiftMenu menu : giftMenus.keySet()) {
            System.out.printf((ORDER_MENU_FORMAT) + "%n", menu.getName(), giftMenus.get(menu));
        }
    }

    private static void printBenefits(final Map<String, Long> benefits) {
        System.out.println(System.lineSeparator() + BENEFITS_ITLE);

        if (benefits.isEmpty()) {
            System.out.println(NON_EXISTS);
            return;
        }
        for (final String eventName : benefits.keySet()) {
            System.out.printf((BENEFITS_FORMAT) + "%n", eventName, benefits.get(eventName));
        }
    }

    private static void printTotalBenefitPrize(final EventBenefits benefits) {
        final long totalBenefitPrize = benefits.calculateTotalPrize();
        System.out.println(System.lineSeparator() + TOTAL_BENEFIT_PRIZE_TITLE);

        if (totalBenefitPrize == 0L) {
            System.out.println(NON_EXISTS_PRICE);
            return;
        }
        System.out.printf((TOTAL_BENEFIT_PRIZE_FORMAT) + "%n", totalBenefitPrize);
    }

    private static void printEstimatedPaymentPrize(
            final EventBenefits benefits,
            final Order order,
            final GiftMenus giftMenus
    ) {
        long estimatedPaymentPrize = benefits.calculateEstimatedPaymentPrize(order, giftMenus);
        System.out.println(System.lineSeparator() + ESTIMATED_PAYMENT_PRIZE_TITLE);
        System.out.printf((PRICE_FORMAT) + "%n", estimatedPaymentPrize);
    }

    private static void printEventBadge(final EventBenefits benefits) {
        final EventBadge badge = benefits.publishBadge();
        System.out.println(System.lineSeparator() + EVENT_BADGE_TITLE);
        System.out.printf(badge.getName());
    }
}
