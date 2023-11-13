package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuBoardTest {
    @Test
    @DisplayName("getSort() 유효성 테스트")
    public void 이름과_sort가_서로_일치하면_정상_실행() {
        // Given
        MenuBoard appetizer = MenuBoard.양송이수프;
        MenuBoard main = MenuBoard.티본스테이크;
        MenuBoard dessert = MenuBoard.초코케이크;
        MenuBoard beverage = MenuBoard.제로콜라;

        // When
        String appetizerSort = appetizer.getSort();
        String mainSort = main.getSort();
        String dessertSort = dessert.getSort();
        String beverageSort = beverage.getSort();

        // Then
        assertEquals("Appetizer", appetizerSort);
        assertEquals("Main", mainSort);
        assertEquals("Dessert", dessertSort);
        assertEquals("Beverage", beverageSort);
    }

    @Test
    @DisplayName("getPrice() 유효성 테스트")
    public void 이름과_price가_서로_일치하면_정상_실행() {
        // Given
        MenuBoard soup = MenuBoard.양송이수프;
        MenuBoard steak = MenuBoard.티본스테이크;
        MenuBoard cake = MenuBoard.초코케이크;
        MenuBoard cola = MenuBoard.제로콜라;

        // When
        int soupPrice = soup.getPrice();
        int steakPrice = steak.getPrice();
        int cakePrice = cake.getPrice();
        int colaPrice = cola.getPrice();

        // Then
        assertEquals(6_000, soupPrice);
        assertEquals(55_000, steakPrice);
        assertEquals(15_000, cakePrice);
        assertEquals(3_000, colaPrice);
    }
}
