package christmas.domain.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDay {
    private final LocalDate date;

    public VisitingDay(int day) {
        this.date = LocalDate.of(2023, 12, day);
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public boolean isEqual(LocalDate otherDate) {
        return date.isEqual(otherDate);
    }
}
