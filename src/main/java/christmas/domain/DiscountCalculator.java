package christmas.domain;

import christmas.utils.Constants;
import christmas.view.OutputView;
import java.util.stream.Stream;

public class DiscountCalculator {
    public static int totalDiscount = 0;
    public static int giveawayBenefit = 0;
    public static int totalBenefitAmount = 0;
    public static int champagneForGiveaway = 0;

    public void calculateGiveAwayEvent(Order order) {
        if (order.isTotalPriceAboveThreshold() && order.hasValidTotalPriceForEvents()) {
            giveawayBenefit -= Constants.CHAMPAGNE_PRICE;
            champagneForGiveaway++;
        }
    }

    public static int calculateDDayDiscount(VisitingDate visitingDate, Order order) {
        return visitingDate.calculateDDayDiscountAmount(order);
    }

    public static int calculateEveryDayDiscount(Order order, VisitingDate visitingDate) {
        int discountAmount = 0;

        if (visitingDate.isWeekend() && order.hasValidTotalPriceForEvents()) {
            discountAmount = order.getMainCount() * Constants.WEEKEND_DISCOUNT_AMOUNT;
        }
        if (!visitingDate.isWeekend() && order.hasValidTotalPriceForEvents()) {
            discountAmount = order.getDessertCount() * Constants.WEEKDAY_DISCOUNT_AMOUNT;
        }

        return discountAmount;
    }


    public static int calculateSpeicalDiscount(VisitingDate visitingDate, Order order) {
        return visitingDate.calculateSpecialDiscountAmount(order);
    }

    public static void calculateTotalBenefitAmount() {
        totalBenefitAmount = totalDiscount + giveawayBenefit;
    }

    public static int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public static int calculateDdayDiscountAmount(VisitingDate visitingDate, Order order) {
        int dDayDiscountAmount = visitingDate.calculateDDayDiscountAmount(order);
        totalDiscount = totalDiscount + dDayDiscountAmount;
        return dDayDiscountAmount;
    }

    public void calculateDiscountAmount(VisitingDate visitingDate, Order order) {
        int dDayDiscountAmount = calculateDDayDiscount(visitingDate, order);
        int everyDayDiscountAmount = calculateEveryDayDiscount(order, visitingDate);
        int specialDiscountAmount = visitingDate.calculateSpecialDiscountAmount(order);

        if (!isEligibleForEvents(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount, order)) {
            OutputView.printMessage("없음");
            OutputView.printNewLine();
            return;
        }
        updateTotalDiscount(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount);
    }

    private static boolean isEligibleForEvents(int dDayDiscountAmount, int everyDayDiscountAmount,
                                               int specialDiscountAmount,
                                               Order order) {
        return Stream.of(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount)
                .mapToInt(Integer::intValue)
                .sum() < 0 && order.hasValidTotalPriceForEvents();
    }

    public static void updateTotalDiscount(int dDayDiscountAmount, int everyDayDiscountAmount,
                                           int specialDiscountAmount) {
        totalDiscount = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;
    }

    public static int getChampagneForGiveaway() {
        return champagneForGiveaway;
    }

    public static int getGiveawayBenefit() {
        return giveawayBenefit;
    }
}

