package christmas.domain.date;

import christmas.utils.Constants;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDate {
    private final VisitingDay visitingDay;

    public VisitingDate(int day) {
        this.visitingDay = new VisitingDay(day);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitingDay.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isDdayDiscountPeriod() {
        int day = visitingDay.getDayOfMonth();
        return day >= Constants.FIRST_DAY_FOR_DDAY_EVENT && day <= Constants.LAST_DAY_FOR_DDAY_EVENT;
    }

    public boolean isSpecialDiscountDay() {
        DayOfWeek dayOfWeek = visitingDay.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || visitingDay.isEqual(LocalDate.of(2023, 12, 25));
    }

    public int getDayOfMonth() {
        return visitingDay.getDayOfMonth();
    }
}
