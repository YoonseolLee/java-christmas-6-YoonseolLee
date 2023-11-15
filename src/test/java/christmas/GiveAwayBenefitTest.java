package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.wrapped.GiveAwayBenefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveAwayBenefitTest {

    @DisplayName("getValue 유효성 검사")
    @Test
    void 선언한_값과_getValue한_값이_동일하면_정상실행() {
        // Given
        int initialBenefitValue = 1000;

        // When
        GiveAwayBenefit giveAwayBenefit = new GiveAwayBenefit(initialBenefitValue);

        // Then
        assertEquals(initialBenefitValue, giveAwayBenefit.getValue());
    }

    @DisplayName("subtract() 유효성 테스트")
    @Test
    void initialBenefitValue_에서_subtractAmount를_뺀_값이_유효하면_정상실행() {
        // Given
        int initialBenefitValue = 1000;
        int subtractAmount = 500;
        GiveAwayBenefit giveAwayBenefit = new GiveAwayBenefit(initialBenefitValue);

        // When
        GiveAwayBenefit newBenefit = giveAwayBenefit.subtract(subtractAmount);

        // Then
        assertEquals(initialBenefitValue - subtractAmount, newBenefit.getValue());
    }
}






