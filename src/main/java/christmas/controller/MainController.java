package christmas.controller;

import christmas.domain.date.VisitingDate;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.EventApplier;
import christmas.domain.event.TotalBenefitAmountCalculator;
import christmas.domain.event.badge.GrantedEventBadge;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.validation.OrderValidation;
import christmas.utils.GameMessage;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class MainController {
    DiscountCalculator discountCalculator = new DiscountCalculator();
    EventApplier eventApplier = new EventApplier();
    TotalBenefitAmountCalculator totalBenefitAmountCalculator = new TotalBenefitAmountCalculator();
    OrderValidation orderValidation = new OrderValidation();

    public void start() {
        VisitingDate visitingDate = receiveVisitingDate();
        Order order = generateOrderDetails(visitingDate, orderValidation);
        applyGiveawayEvent(eventApplier, order);
        applyDiscounts(discountCalculator, visitingDate, order, eventApplier);
        int totalBenefitAmount = applyTotalBenefitAmount(totalBenefitAmountCalculator, discountCalculator);
        applyTotalPriceAfterDiscount(totalBenefitAmount, order);
        GrantedEventBadge badge = applyEventBadge(totalBenefitAmount);
    }

    private VisitingDate receiveVisitingDate() {
        VisitingDate visitingDate = InputView.getVisitingDate();
        return visitingDate;
    }

    private Order generateOrderDetails(VisitingDate visitingDate, OrderValidation orderValidation) {
        Order order = receiveOrder(orderValidation);
        printOrderedMenus(visitingDate, order.getOrderedMenus());
        printTotalPriceBeforeDiscount(order);
        return order;
    }

    private Order receiveOrder(OrderValidation orderValidation) {
        Order order = InputView.getOrder(orderValidation);
        return order;
    }

    private void applyGiveawayEvent(EventApplier eventApplier, Order order) {
        eventApplier.calculateGiveAwayEvent(order);
        OutputView.printGiveaway(eventApplier);
    }

    private void applyDiscounts(DiscountCalculator calculator, VisitingDate visitingDate, Order order,
                                EventApplier eventApplier) {
        OutputView.printMessage(GameMessage.BENEFIT_DETAILS.getMessage());
        Map<String, Integer> discountAmounts = calculator.calculateDiscountAmount(visitingDate, order);
        printDiscountOrMessage(discountAmounts, visitingDate, order, calculator, eventApplier);
    }


    private void printDiscountOrMessage(Map<String, Integer> discountAmounts, VisitingDate visitingDate, Order order,
                                        DiscountCalculator calculator, EventApplier eventApplier) {
        int dDayDiscountAmount = discountAmounts.getOrDefault("D-Day Discount Amount", 0);
        int everyDayDiscountAmount = discountAmounts.getOrDefault("Every Day Discount Amount", 0);
        int specialDiscountAmount = discountAmounts.getOrDefault("Special Discount Amount", 0);

        int totalDiscount = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;

        if (totalDiscount == 0) {
            OutputView.printMessage(GameMessage.NONE_MESSAGE.getMessage());
            OutputView.printNewLine();
        }

        if (totalDiscount != 0 && calculator.isEligibleForEvents(dDayDiscountAmount, everyDayDiscountAmount,
                specialDiscountAmount, order)) {
            OutputView.printDiscount(discountAmounts, visitingDate, eventApplier.getGiveawayBenefit().getValue());
        }
    }

    private int applyTotalBenefitAmount(TotalBenefitAmountCalculator totalBenefitAmountCalculator,
                                        DiscountCalculator discountCalculator) {
        totalBenefitAmountCalculator.calculateTotalBenefitAmount(discountCalculator, eventApplier);
        int totalBenefitAmount = totalBenefitAmountCalculator.getTotalBenefit().getValue();
        OutputView.printTotalBenefitAmount(totalBenefitAmount);
        return totalBenefitAmount;
    }

    private void applyTotalPriceAfterDiscount(int totalBenefitAmount, Order order) {
        int totalPriceAfterDiscount = order.getTotalPriceAfterDiscount(totalBenefitAmount);
        OutputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }

    private GrantedEventBadge applyEventBadge(int totalBenefitAmount) {
        GrantedEventBadge grantedEventBadge = GrantedEventBadge.of(totalBenefitAmount);
        OutputView.printEventBadge(grantedEventBadge.getBadge());
        return grantedEventBadge;
    }


    private void printOrderedMenus(VisitingDate visitingDate, List<Menu> orderedMenus) {
        OutputView.printEventPreview(visitingDate);
        OutputView.printOrderedMenus(orderedMenus);
    }

    private void printTotalPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscount(order);
    }
}
