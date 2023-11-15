package christmas.domain.event;

import christmas.domain.date.VisitingDate;
import christmas.domain.order.Order;
import christmas.utils.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class DiscountCalculator {
    public static int totalDiscount = 0;

    public int calculateDDayDiscountAmount(VisitingDate visitingDate, Order order) {
        if (!visitingDate.isDdayDiscountPeriod() || !order.hasValidTotalPriceForEvents()) {
            return 0;
        }
        int day = visitingDate.getDayOfMonth();
        return -(1000 + (day - 1) * 100);
    }

    public static int calculateEveryDayDiscount(Order order, VisitingDate visitingDate) {
        int discountAmount = 0;

        if (visitingDate.isWeekend() && order.hasValidTotalPriceForEvents()) {
            discountAmount = order.getMainCount() * Constants.WEEKEND_DISCOUNT_AMOUNT;
        }
        if (!visitingDate.isWeekend() && order.hasValidTotalPriceForEvents()) {
            discountAmount = order.getDessertCount() * Constants.WEEKDAY_DISCOUNT_AMOUNT;
        }
        if (!visitingDate.isWeekend() && !order.hasValidTotalPriceForEvents()) {
            discountAmount += 0;
        }
        if (!visitingDate.isWeekend() && !order.hasValidTotalPriceForEvents()) {
            discountAmount += 0;
        }
        return discountAmount;
    }

    public int calculateSpecialDiscountAmount(VisitingDate visitingDate, Order order) {
        if (!visitingDate.isSpecialDiscountDay() || !order.hasValidTotalPriceForEvents()) {
            return 0;
        }
        return -1000;
    }

    public Map<String, Integer> calculateDiscountAmount(VisitingDate visitingDate, Order order) {
        Map<String, Integer> discountAmounts = new HashMap<>();
        int dDayDiscountAmount = calculateDDayDiscountAmount(visitingDate, order);
        int everyDayDiscountAmount = calculateEveryDayDiscount(order, visitingDate);
        int specialDiscountAmount = calculateSpecialDiscountAmount(visitingDate, order);
        updateTotalDiscount(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount);

        discountAmounts.put("D-Day Discount Amount", dDayDiscountAmount);
        discountAmounts.put("Every Day Discount Amount", everyDayDiscountAmount);
        discountAmounts.put("Special Discount Amount", specialDiscountAmount);
        return discountAmounts;
    }

    public static boolean isEligibleForEvents(int dDayDiscountAmount, int everyDayDiscountAmount,
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
}

