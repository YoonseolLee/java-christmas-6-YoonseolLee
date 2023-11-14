package christmas.domain;

import christmas.view.OutputView;

public class DiscountCalculator {
    private static final int WEEKEND_DISCOUNT_AMOUNT = -2023;
    private static final int WEEKDAY_DISCOUNT_AMOUNT = -2023;
    public static int totalDiscount = 0;
    public static int giveawayBenefit = 0;
    public static int totalBenefitAmount = 0;

    public static void updateTotalDiscount(int dDayDiscountAmount, int everyDayDiscountAmount,
                                           int specialDiscountAmount) {
        totalDiscount = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;
    }

    public static int getGiveawayBenefit() {
        return giveawayBenefit;
    }

    public static int champagneForFree = 0;
    private static boolean isGiveawayPriceCalculated = false;

    public static void calculateGiveAwayEvent(Order order) {
        if (order.isTotalPriceAboveThreshold()) {
            giveawayBenefit -= 25000;
            champagneForFree++;
        }
    }

    public static void printGiveaway() {
        if (champagneForFree == 0) {
            OutputView.printNewLine();
            OutputView.printMessage("<증정 메뉴>");
            System.out.println("없음");
            return;
        }

        if (champagneForFree > 0) {
            OutputView.printGiveaways(champagneForFree);
        }
    }

    public static void printGiveawayBenefit() {
        OutputView.printGiveawayBenefit(giveawayBenefit);
    }

    public static int calculateDDayDiscount(VisitingDate visitingDate) {
        return visitingDate.calculateDDayDiscountAmount();
    }

    public static int calculateEveryDayDiscount(Order order, VisitingDate visitingDate) {
        if (visitingDate.isWeekend()) {
            return order.getMainCount() * WEEKEND_DISCOUNT_AMOUNT;
        } else {
            return order.getDessertCount() * WEEKDAY_DISCOUNT_AMOUNT;
        }
    }


    public static int calculateSpeicalDiscount(VisitingDate visitingDate) {
        return visitingDate.calculateSpecialDiscountAmount();
    }


    public static void calculateTotalBenefitAmount() {
        totalBenefitAmount = totalDiscount + giveawayBenefit;
    }

    public static int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public static int calculateDdayDiscountAmount(VisitingDate visitingDate) {
        int dDayDiscountAmount = visitingDate.calculateDDayDiscountAmount();
        totalDiscount = totalDiscount + dDayDiscountAmount;
        return dDayDiscountAmount;
    }
}

