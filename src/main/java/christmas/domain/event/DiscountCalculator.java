package christmas.domain.event;

import christmas.domain.date.VisitingDate;
import christmas.domain.event.wrapped.Discount;
import christmas.domain.order.Order;
import christmas.utils.Constants;
import java.util.Map;
import java.util.stream.Stream;

public class DiscountCalculator {
    private Discount totalDiscount;

    public DiscountCalculator() {
        this.totalDiscount = new Discount(0);
    }

    public Map<String, Integer> calculateDiscountAmount(VisitingDate visitingDate, Order order) {
        int dDayDiscountAmount = calculateDDayDiscountAmount(visitingDate, order);
        int everyDayDiscountAmount = calculateEveryDayDiscount(order, visitingDate);
        int specialDiscountAmount = calculateSpecialDiscountAmount(visitingDate, order);
        updateTotalDiscount(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount);

        return Map.of(
                "D-Day Discount Amount", dDayDiscountAmount,
                "Every Day Discount Amount", everyDayDiscountAmount,
                "Special Discount Amount", specialDiscountAmount
        );
    }

    public boolean isEligibleForEvents(int dDayDiscountAmount, int everyDayDiscountAmount,
                                       int specialDiscountAmount, Order order) {
        return Stream.of(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount)
                .mapToInt(Integer::intValue)
                .sum() < 0 && order.hasValidTotalPriceForEvents();
    }

    public void updateTotalDiscount(int dDayDiscountAmount, int everyDayDiscountAmount,
                                    int specialDiscountAmount) {
        totalDiscount = new Discount(dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount);
    }

    public Discount getTotalDiscount() {
        return totalDiscount;
    }

    private int calculateDDayDiscountAmount(VisitingDate visitingDate, Order order) {
        if (!visitingDate.isDdayDiscountPeriod() || !order.hasValidTotalPriceForEvents()) {
            return 0;
        }
        int day = visitingDate.getDayOfMonth();
        return -(1000 + (day - 1) * 100);
    }

    private int calculateEveryDayDiscount(Order order, VisitingDate visitingDate) {
        if (order.hasValidTotalPriceForEvents()) {
            return visitingDate.isWeekend()
                    ? order.getMainCount() * Constants.WEEKEND_DISCOUNT_AMOUNT
                    : order.getDessertCount() * Constants.WEEKDAY_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    private int calculateSpecialDiscountAmount(VisitingDate visitingDate, Order order) {
        if (!visitingDate.isSpecialDiscountDay() || !order.hasValidTotalPriceForEvents()) {
            return 0;
        }
        return Constants.SPECIAL_DISCOUNT_AMOUNT;
    }
}

