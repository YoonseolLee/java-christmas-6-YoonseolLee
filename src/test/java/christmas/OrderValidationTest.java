package christmas;

import christmas.domain.validation.OrderValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderValidationTest {
    OrderValidation orderValidation = new OrderValidation();

    @ParameterizedTest
    @ValueSource(strings = {
            "양송이수프-16,타파스-1,레드와인-3",
            "초코케이크-1,아이스크림-2",
            "시저샐러드-1",
            "크리스마스파스타-5,레드와인-3"
    })
    @DisplayName("isValidOrder 테스트_정상적인 경우")
    void 정상적인_주문이면_정상_실행(String validOrder) {
        Assertions.assertTrue(orderValidation.isValidOrder(validOrder));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "초코케이크-1,아이스크림 2",
            "시저샐러드-1, 제로콜라-19",
            "크리스마스파스타-15 + 레드와인-15"
    })
    @DisplayName("hasValidQuantityFormat 테스트")
    void 주문포맷이_유효하지_않은_경우_예외_발생(String invalidFormatOrder) {
        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderValidation.isValidOrder(invalidFormatOrder));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "초코케이크 -1,아이스크림-2,레드와인- 1",
            "시저샐러드- 1",
            "제로콜라 -3",
            "크리스마스파스타-5,레드와인 -3"
    })
    @DisplayName("hasNoExtraSpaces 테스트")
    void 메뉴에_공백이_있는_경우_예외_발생(String orderWithExtraSpaces) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderValidation.isValidOrder(orderWithExtraSpaces));
    }

    @Test
    @DisplayName("hasValidFormat 테스트")
    void 주문이_비었으면_예외_발생() {
        // Given
        String emptyMenuList = "";

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderValidation.isValidOrder(emptyMenuList));
    }

    @Test
    @DisplayName("hasValidQuantityFormat 테스트")
    void 메뉴의_수량의_총합이_20개_이상인_경우_예외_발생() {
        // Given
        String invalidQuantityFormatOrder = "양송이수프-2,타파스-20,레드와인-3";

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderValidation.isValidOrder(invalidQuantityFormatOrder));
    }

    @Test
    @DisplayName("hasValidQuantityFormat 테스트")
    void 메뉴의_수량이_0인_경우_예외_발생() {
        // Given
        String zeroQuantityOrder = "양송이수프-2,타파스-0,레드와인-3";

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderValidation.isValidOrder(zeroQuantityOrder));
    }

    @Test
    @DisplayName("containsOnlyExistingMenus 테스트")
    void 메뉴_중에_존재하지_않는_메뉴가_있는_경우_예외_발생() {
        // Given
        String nonExistingMenuOrder = "양송이수프-2,커피-1,레드와인-3";

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderValidation.isValidOrder(nonExistingMenuOrder));
    }

    @Test
    @DisplayName("hasNoDuplicateMenus 테스트")
    void 메뉴가_중복되면_예외_발생() {
        // Given
        String duplicateMenusOrder = "양송이수프-2,타파스-1,레드와인-3,양송이수프-1";

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderValidation.isValidOrder(duplicateMenusOrder));
    }

    @Test
    @DisplayName("doesNotExceedMaxMenuCount 테스트")
    void 총_주문_수량이_20개를_초과하는_경우_예외_발생() {
        // Given
        String exceedingMaxMenuCountOrder = "양송이수프-2,타파스-1,레드와인-3,초코케이크-15";

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderValidation.isValidOrder(exceedingMaxMenuCountOrder));
    }

    @Test
    @DisplayName("includesNonBeverageItem 테스트")
    void 모든_메뉴가_음료인_경우_예외_발생() {
        // Given
        String AllDrinksMenuOrder = "샴페인-1,레드와인-3,제로콜라-15";

        // When, Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> orderValidation.isValidOrder(AllDrinksMenuOrder));
    }
}
