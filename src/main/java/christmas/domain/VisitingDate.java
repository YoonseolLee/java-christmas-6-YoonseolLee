package christmas.domain;

import christmas.view.OutputView;
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

    public void printEventPreview() {
        OutputView.printMessage("12월 " + visitingDate.getDayOfMonth() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        OutputView.printNewLine();
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitingDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public int calculateDDayDiscountAmount() {
        if (!isDdayDiscountPeriod()) {
            return 0;
        }
        int day = visitingDate.getDayOfMonth();
        return 1000 + (day - 1) * 100;
    }

    public boolean isDdayDiscountPeriod() {
        int day = visitingDate.getDayOfMonth();
        return day >= 1 && day <= 25;
    }
}
