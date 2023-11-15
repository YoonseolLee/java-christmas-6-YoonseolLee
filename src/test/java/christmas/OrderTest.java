package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.Menu;
import christmas.domain.MenuBoard;
import christmas.domain.Order;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("hasValidTotalPriceForEvents 테스트 - 1만원 이상")
    @Test
    void 금액총합이_1만원_이상이면_true_리턴() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.해산물파스타, 2),
                new Menu(MenuBoard.레드와인, 1),
                new Menu(MenuBoard.초코케이크, 1)
        ));

        assertTrue(order.hasValidTotalPriceForEvents());
    }

    @DisplayName("hasValidTotalPriceForEvents 테스트 - 1만원 미만")
    @Test
    void 금액총합이_1만원_미만이면_false_리턴() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.제로콜라, 1),
                new Menu(MenuBoard.양송이수프, 1)
        ));

        assertFalse(order.hasValidTotalPriceForEvents());
    }

    @DisplayName("getDessertCount 테스트")
    @Test
    void 디저트의_수량을_정확히_가져오면_true_반환() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.초코케이크, 2),
                new Menu(MenuBoard.아이스크림, 1),
                new Menu(MenuBoard.레드와인, 1)
        ));

        assertEquals(3, order.getDessertCount());
    }

    @DisplayName("getMainCount 테스트")
    @Test
    void 메인메뉴의_수량을_정확히_가져오면_true_반환() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.초코케이크, 10),
                new Menu(MenuBoard.티본스테이크, 3),
                new Menu(MenuBoard.레드와인, 5)
        ));

        assertEquals(3, order.getMainCount());
    }

    @DisplayName("isTotalPriceAboveThreshold 테스트-12만원 이상")
    @Test
    void 금액총합이_12만원_이상이면_True_반환() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.초코케이크, 10),
                new Menu(MenuBoard.레드와인, 7)
        ));

        assertTrue(order.isTotalPriceAboveThreshold());
    }

    @DisplayName("isTotalPriceAboveThreshold 테스트-12만원 이하")
    @Test
    void 금액총합이_12만원_미만이면_False_반환() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.초코케이크, 1),
                new Menu(MenuBoard.레드와인, 1)
        ));

        assertFalse(order.isTotalPriceAboveThreshold());
    }

    @DisplayName("getTotalPriceBeforeDiscount 테스트 - 12만원 미만")
    @Test
    void 금액총합이_12만원_미만이면_증정이벤트_적용안함() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.초코케이크, 10),
                new Menu(MenuBoard.티본스테이크, 1)
        ));

        int totalBenefitAmount = 5000;
        int expectedTotalPriceAfterDiscount = order.getTotalPriceBeforeDiscount() - (-totalBenefitAmount) + 25000;

        assertEquals(expectedTotalPriceAfterDiscount, order.getTotalPriceAfterDiscount(totalBenefitAmount));
    }

    @DisplayName("getTotalPriceBeforeDiscount 테스트 - 12만원 이상")
    @Test
    void 금액총합이_12만원_이상이면_증정이벤트_적용함() {
        Order order = createOrder(Arrays.asList(
                new Menu(MenuBoard.초코케이크, 1),
                new Menu(MenuBoard.레드와인, 1)
        ));

        int totalBenefitAmount = 5000;
        int expectedTotalPriceAfterDiscount = order.getTotalPriceBeforeDiscount() - (-totalBenefitAmount);

        assertEquals(expectedTotalPriceAfterDiscount, order.getTotalPriceAfterDiscount(totalBenefitAmount));
    }

    private Order createOrder(List<Menu> orderedMenus) {
        return new Order(Arrays.asList("초코케이크", "레드와인"), orderedMenus);
    }
}
