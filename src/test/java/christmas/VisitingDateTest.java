package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.VisitingDate;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class VisitingDateTest {

    @Test
    void testVisitingDateCreation() {
        // Given
        int day = 25; // 예시로 25일을 선택

        // When
        VisitingDate visitingDate = new VisitingDate(day);

        // Then
        LocalDate expectedDate = LocalDate.of(2023, 12, day);
        assertEquals(expectedDate, visitingDate.getVisitingDate());
    }
}
