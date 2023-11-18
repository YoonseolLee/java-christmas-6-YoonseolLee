package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.event.wrapped.Discount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {

    @DisplayName("getValue() 유효성 테스트")
    @Test
    void 선언한_값과_getValue한_값이_동일하면_정상실행() {
        // Given
        int discountValue = 500;

        // When
        Discount discount = new Discount(discountValue);

        // Then
        assertEquals(discountValue, discount.getValue());
    }
}
