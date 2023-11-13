package christmas.domain;

import christmas.domain.validation.VisitingDateValidation;
import christmas.view.OutputView;
import java.time.LocalDate;

public class VisitingDate {
    public LocalDate getVisitingDate() {
        return visitingDate;
    }

    private LocalDate visitingDate;

    public VisitingDate(int day) {
        VisitingDateValidation.validateVisitingDate(day);
        this.visitingDate = LocalDate.of(2023, 12, day);
    }

    public void printEventAnnae() {
        OutputView.printMessage("12월 " + visitingDate.getDayOfMonth() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        OutputView.printNewLine();
    }
}
