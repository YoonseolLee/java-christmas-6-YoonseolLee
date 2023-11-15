package christmas;

import christmas.controller.MainController;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.EventApplier;
import christmas.domain.event.TotalBenefitAmountCalculator;
import christmas.domain.manager.RestaurantManager;
import christmas.domain.validation.OrderValidation;

public class Application {
    public static void main(String[] args) {
        new MainController(
                new DiscountCalculator(),
                new EventApplier(),
                new TotalBenefitAmountCalculator(),
                new OrderValidation(),
                new RestaurantManager()
        ).start();
    }
}

