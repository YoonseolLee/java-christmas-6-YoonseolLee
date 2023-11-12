package christmas.domain;

import christmas.domain.validation.VisitingDateValidation;
import java.time.LocalDate;

public class VisitingDate {
    private LocalDate visitingDate;

    public VisitingDate(int day) {
        VisitingDateValidation.validateVisitingDate(day);
        this.visitingDate = LocalDate.of(2023, 12, day);
    }
}
