package christmas;

import christmas.controller.MainController;
import christmas.domain.PromotionManager;

public class Application {
    public static void main(String[] args) {
        MainController controller = new MainController(new PromotionManager());
        controller.start();
    }
}
