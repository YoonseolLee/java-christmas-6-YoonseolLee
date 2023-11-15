package christmas.domain.date;

import christmas.utils.Constants;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDate {

    private LocalDate visitingDate;

    public VisitingDate(int day) {
        this.visitingDate = LocalDate.of(2023, 12, day);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isDdayDiscountPeriod() {
        int day = visitingDate.getDayOfMonth();
        return day >= Constants.FIRST_DAY_FOR_DDAY_EVENT && day <= Constants.LAST_DAY_FOR_DDAY_EVENT;
    }

    public boolean isSpecialDiscountDay() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || visitingDate.isEqual(LocalDate.of(2023, 12, 25));
    }

    public int getDayOfMonth() {
        return visitingDate.getDayOfMonth();
    }
}
