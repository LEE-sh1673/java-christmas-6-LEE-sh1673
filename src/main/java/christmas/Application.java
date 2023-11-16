package christmas;

import christmas.controller.EventPromotionController;

public class Application {
    public static void main(String[] args) {
        final EventPromotionController controller = new EventPromotionController();
        controller.run();
    }
}
