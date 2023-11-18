package christmas;

import christmas.domain.validation.VisitingDateValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitingDateTest {
    @ParameterizedTest
    @ValueSource(strings = {"00", "  ", "!", "32", "-1", ""})
    @DisplayName("isValidDate 테스트")
    void 입력한_날짜가_유효하지_않으면_예외_발생(String invalidDate) {
        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> VisitingDateValidation.isValidDate(invalidDate));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 33, 100})
    @DisplayName("isValidDate 테스트")
    void 방문_날짜가_유효한_범위를_넘어가면_예외_발생(int invalidDay) {
        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> VisitingDateValidation.isValidDate(String.valueOf(invalidDay)));
    }
}
