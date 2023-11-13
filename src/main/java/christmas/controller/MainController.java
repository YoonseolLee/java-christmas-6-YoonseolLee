package christmas.controller;

import christmas.domain.Order;
import christmas.domain.PromotionManager;
import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {
    private final PromotionManager manager;

    public MainController(final PromotionManager manager) {
        this.manager = manager;
    }

    public void start() {
        OutputView.printGreetings();
        VisitingDate visitingDate = InputView.getVisitingDate();
        Order order = InputView.getOrder();
        visitingDate.printEventAnnae();
        order.printOrderedMenus();
        order.printTotalPriceBeforeDiscount();
    }
}
