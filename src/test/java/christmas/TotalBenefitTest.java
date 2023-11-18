package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.wrapped.TotalBenefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalBenefitTest {

    @DisplayName("getValue() 유효성 테스트")
    @Test
    void 선언한_값과_getValue한_값이_동일하면_정상실행() {
        // Given
        int initialBenefitValue = 1500;

        // When
        TotalBenefit totalBenefit = new TotalBenefit(initialBenefitValue);

        // Then
        assertEquals(initialBenefitValue, totalBenefit.getValue());
    }
}
