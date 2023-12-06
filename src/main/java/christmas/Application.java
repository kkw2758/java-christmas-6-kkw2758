package christmas;

import christmas.controller.ChristmasPromotionController;

public class Application {
    public static void main(String[] args) {
        ChristmasPromotionController.getInstance().run();
    }
}
