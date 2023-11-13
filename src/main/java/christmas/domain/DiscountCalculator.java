package christmas.domain;

public class DiscountCalculator {
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;
    private static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    public static int totalDiscount = 0;

    public static int getTotalDiscount() {
        return totalDiscount;
    }


    public static int calculateEveryDayDiscount(Order order, VisitingDate visitingDate) {
        int everyDayDiscountAmount = order.getMainCount() * WEEKEND_DISCOUNT_AMOUNT;
        if (!visitingDate.isWeekend()) {
            everyDayDiscountAmount = order.getDessertCount() * WEEKDAY_DISCOUNT_AMOUNT;
        }
        totalDiscount = totalDiscount + everyDayDiscountAmount;
        return everyDayDiscountAmount;
    }

    public static int calculateDDayDiscount(VisitingDate visitingDate) {
        int dDayDiscountAmount = visitingDate.calculateDDayDiscountAmount();
        totalDiscount = totalDiscount + dDayDiscountAmount;
        return dDayDiscountAmount;
    }

    public static int calculateSpeicalDiscount(VisitingDate visitingDate) {
        int specialDiscountAmount = visitingDate.calculateSpecialDiscountAmount();
        totalDiscount = totalDiscount + specialDiscountAmount;
        return specialDiscountAmount;
    }
}

