package christmas.controller;

import christmas.domain.DiscountCalculator;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {
    public void start() {
        OutputView.printGreetings();
        VisitingDate visitingDate = InputView.getVisitingDate();
        Order order = InputView.getOrder();
        visitingDate.printEventPreview();
        order.printOrderedMenus();
        order.printTotalPriceBeforeDiscount();
        DiscountCalculator.calculateEveryDayDiscount(order, visitingDate);
    }
}
