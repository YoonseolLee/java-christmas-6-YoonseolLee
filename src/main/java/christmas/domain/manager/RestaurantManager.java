package christmas.domain.manager;

import christmas.domain.date.VisitingDate;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.EventApplier;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.utils.GameMessage;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RestaurantManager {
    public void applyGiveawayEvent(EventApplier eventApplier, Order order) {
        eventApplier.calculateGiveAwayEvent(order);
        OutputView.printGiveaway(eventApplier);
    }

    public void applyDiscounts(DiscountCalculator calculator, VisitingDate visitingDate, Order order,
                               EventApplier eventApplier) {
        OutputView.printMessage(GameMessage.BENEFIT_DETAILS.getMessage());
        Map<String, Integer> discountAmounts = calculator.calculateDiscountAmount(visitingDate, order);
        printDiscountOrNoDiscountMessage(discountAmounts, visitingDate, order, calculator, eventApplier);
    }

    public void printOrderedMenus(VisitingDate visitingDate, List<Menu> orderedMenus) {
        OutputView.printEventPreview(visitingDate);
        OutputView.printOrderedMenus(orderedMenus);
    }

    public void printTotalPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscount(order);
    }

    public void applyTotalPriceAfterDiscount(int totalBenefitAmount, Order order) {
        int totalPriceAfterDiscount = order.getTotalPriceAfterDiscount(totalBenefitAmount);
        OutputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }

    public void printEventBadge(String badge) {
        OutputView.printEventBadge(badge);
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        OutputView.printTotalBenefitAmount(totalBenefitAmount);
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
}
