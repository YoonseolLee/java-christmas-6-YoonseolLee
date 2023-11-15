package christmas.controller;

import christmas.domain.date.VisitingDate;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.EventApplier;
import christmas.domain.event.TotalBenefitAmountCalculator;
import christmas.domain.event.badge.GrantedEventBadge;
import christmas.domain.manager.RestaurantManager;
import christmas.domain.order.Order;
import christmas.domain.validation.OrderValidation;
import christmas.view.InputView;

public class MainController {
    private DiscountCalculator discountCalculator;
    private EventApplier eventApplier;
    private TotalBenefitAmountCalculator totalBenefitAmountCalculator;
    private OrderValidation orderValidation;
    private RestaurantManager manager;

    public MainController(DiscountCalculator discountCalculator, EventApplier eventApplier,
                          TotalBenefitAmountCalculator totalBenefitAmountCalculator, OrderValidation orderValidation,
                          RestaurantManager manager) {
        this.discountCalculator = discountCalculator;
        this.eventApplier = eventApplier;
        this.totalBenefitAmountCalculator = totalBenefitAmountCalculator;
        this.orderValidation = orderValidation;
        this.manager = manager;
    }

    public void start() {
        VisitingDate visitingDate = receiveVisitingDate();
        Order order = generateOrderDetails(visitingDate);
        manager.applyGiveawayEvent(eventApplier, order);
        manager.applyDiscounts(discountCalculator, visitingDate, order, eventApplier);
        int totalBenefitAmount = applyTotalBenefitAmount(totalBenefitAmountCalculator);
        manager.applyTotalPriceAfterDiscount(totalBenefitAmount, order);
        GrantedEventBadge badge = applyEventBadge(totalBenefitAmount);
    }

    private VisitingDate receiveVisitingDate() {
        return InputView.getVisitingDate();
    }

    private Order generateOrderDetails(VisitingDate visitingDate) {
        Order order = InputView.getOrder(orderValidation);
        manager.printOrderedMenus(visitingDate, order.getOrderedMenus());
        manager.printTotalPriceBeforeDiscount(order);
        return order;
    }

    private int applyTotalBenefitAmount(TotalBenefitAmountCalculator totalBenefitAmountCalculator) {
        totalBenefitAmountCalculator.calculateTotalBenefitAmount(discountCalculator, eventApplier);
        int totalBenefitAmount = totalBenefitAmountCalculator.getTotalBenefit().getValue();
        manager.printTotalBenefitAmount(totalBenefitAmount);
        return totalBenefitAmount;
    }

    private GrantedEventBadge applyEventBadge(int totalBenefitAmount) {
        GrantedEventBadge grantedEventBadge = GrantedEventBadge.of(totalBenefitAmount);
        manager.printEventBadge(grantedEventBadge.getBadge());
        return grantedEventBadge;
    }
}
