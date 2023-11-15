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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainController {
    private DiscountCalculator discountCalculator;
    private EventApplier eventApplier;
    private TotalBenefitAmountCalculator totalBenefitAmountCalculator;
    private OrderValidation orderValidation;

    public MainController(DiscountCalculator discountCalculator, EventApplier eventApplier,
                          TotalBenefitAmountCalculator totalBenefitAmountCalculator, OrderValidation orderValidation) {
        this.discountCalculator = discountCalculator;
        this.eventApplier = eventApplier;
        this.totalBenefitAmountCalculator = totalBenefitAmountCalculator;
        this.orderValidation = orderValidation;
    }

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
        printDiscountOrNoDiscountMessage(discountAmounts, visitingDate, order, calculator, eventApplier);
    }

    private void printDiscountOrNoDiscountMessage(Map<String, Integer> discountAmounts, VisitingDate visitingDate,
                                                  Order order,
                                                  DiscountCalculator calculator, EventApplier eventApplier) {
        int totalDiscount = calculateTotalDiscount(discountAmounts);
        if (totalDiscount == 0) {
            printNoDiscountMessage();
            return;
        }
        printDiscountIfEligible(discountAmounts, visitingDate, order, calculator, eventApplier);
    }

    private int calculateTotalDiscount(Map<String, Integer> discountAmounts) {
        return discountAmounts.values().stream().mapToInt(Integer::intValue).sum();
    }

    private void printNoDiscountMessage() {
        OutputView.printMessage(GameMessage.NONE_MESSAGE.getMessage());
        OutputView.printNewLine();
    }

    private void printDiscountIfEligible(Map<String, Integer> discountAmounts, VisitingDate visitingDate, Order order,
                                         DiscountCalculator calculator, EventApplier eventApplier) {
        List<Integer> discountList = Arrays.asList(
                discountAmounts.getOrDefault("D-Day Discount Amount", 0),
                discountAmounts.getOrDefault("Every Day Discount Amount", 0),
                discountAmounts.getOrDefault("Special Discount Amount", 0)
        );
        if (calculator.isEligibleForEvents(discountList.get(0), discountList.get(1), discountList.get(2), order)) {
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
