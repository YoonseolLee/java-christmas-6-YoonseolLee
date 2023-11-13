package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.VisitingDate;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VisitingDateTest {
    @DisplayName("VisitingDate 유효성 검사")
    @Test
    void 일을_입력하면_23년_12월_23일을_반환한다() {
        // Given
        int day = 2;

        // When
        VisitingDate visitingDate = new VisitingDate(day);

        // Then
        LocalDate expectedDate = LocalDate.of(2023, 12, day);
        assertEquals(expectedDate, visitingDate.getVisitingDate());
    }
}
