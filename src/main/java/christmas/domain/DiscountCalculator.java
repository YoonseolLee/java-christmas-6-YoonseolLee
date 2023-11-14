package christmas.domain;

import christmas.utils.Constants;
import christmas.view.OutputView;

public class DiscountCalculator {
    public static int totalDiscount = 0;
    public static int giveawayBenefit = 0;
    public static int totalBenefitAmount = 0;
    public static int champagneForGiveaway = 0;
    private static boolean isGiveawayPriceCalculated = false;

    public static void updateTotalDiscount(int dDayDiscountAmount, int everyDayDiscountAmount,
                                           int specialDiscountAmount) {
        totalDiscount = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;
    }

    public static int getGiveawayBenefit() {
        return giveawayBenefit;
    }

    public static void calculateGiveAwayEvent(Order order) {
        if (order.isTotalPriceAboveThreshold() && order.hasValidTotalPriceForEvents()) {
            giveawayBenefit -= Constants.CHAMPAGNE_PRICE;
            champagneForGiveaway++;
        }
    }

    public static void printGiveaway() {
        if (champagneForGiveaway == 0) {
            OutputView.printNewLine();
            OutputView.printMessage("<증정 메뉴>");
            System.out.println("없음");
            return;
        }

        if (champagneForGiveaway > 0) {
            OutputView.printGiveaways(champagneForGiveaway);
        }
    }

    public static void printGiveawayBenefit() {
        OutputView.printGiveawayBenefit(giveawayBenefit);
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
}

