package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDate {
    public LocalDate getVisitingDate() {
        return visitingDate;
    }

    private LocalDate visitingDate;

    public VisitingDate(int day) {
        this.visitingDate = LocalDate.of(2023, 12, day);
    }

    public int getDayOfMonth() {
        return visitingDate.getDayOfMonth();
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public int calculateDDayDiscountAmount(Order order) {
        if (!isDdayDiscountPeriod() || !order.hasValidTotalPriceForEvents()) {
            return 0;
        }
        int day = visitingDate.getDayOfMonth();
        return -(1000 + (day - 1) * 100);
    }

    public boolean isDdayDiscountPeriod() {
        int day = visitingDate.getDayOfMonth();
        return day >= 1 && day <= 25;
    }

    public int calculateSpecialDiscountAmount(Order order) {
        if (!isSpecialDiscountDay() || !order.hasValidTotalPriceForEvents()) {
            return 0;
        }
        return -1000;
    }

    public boolean isSpecialDiscountDay() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || visitingDate.isEqual(LocalDate.of(2023, 12, 25));
    }
}
