package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.Menu;
import christmas.domain.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {
    @DisplayName("GetMenuBoard() 유효성 검사")
    @Test
    public void testGetMenuBoard() {
        // Given
        MenuBoard expectedMenuBoard = MenuBoard.양송이수프;
        int quantity = 2;

        // When
        Menu menu = new Menu(expectedMenuBoard, quantity);
        MenuBoard actualMenuBoard = menu.getMenuBoard();

        // Then
        assertEquals(expectedMenuBoard, actualMenuBoard);
    }

    @DisplayName("GetQuantity() 유효성 검사")
    @Test
    public void testGetQuantity() {
        // Given
        MenuBoard menuBoard = MenuBoard.타파스;
        int expectedQuantity = 3;

        // When
        Menu menu = new Menu(menuBoard, expectedQuantity);
        int actualQuantity = menu.getQuantity();

        // Then
        assertEquals(expectedQuantity, actualQuantity);
    }
}
