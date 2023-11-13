package christmas.controller;

import christmas.model.GiftMenus;
import christmas.model.Order;
import christmas.model.OrderDate;
import christmas.model.OrderMenus;
import christmas.model.event.EventBenefitPlanner;
import christmas.model.event.EventBenefitPlannerFactory;
import christmas.model.event.EventBenefits;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class EventPromotionController {

    public void run() {
        OutputView.printEventPlannerTitle();

        final Order order = createOrder();
        final GiftMenus giftMenus = GiftMenus.from(order);

        final EventBenefitPlanner planner = EventBenefitPlannerFactory.createDecemberPlanner();
        final EventBenefits benefits = planner.plan(order);

        OutputView.printMenu(order, giftMenus, benefits);
    }

    private Order createOrder() {
        return readWithRetry(() -> new Order(createOrderDate(), createMenus()));
    }

    private OrderDate createOrderDate() {
        return readWithRetry(() -> OrderDate.withDay(InputView.readDate()));
    }

    private OrderMenus createMenus() {
        return readWithRetry(() -> OrderMenus.byNamesWithQuantity(InputView.readMenus()));
    }

    private <T> T readWithRetry(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return readWithRetry(supplier);
        }
    }
}
