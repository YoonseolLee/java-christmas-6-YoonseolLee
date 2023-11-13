package christmas.domain;

public class Menu {
    private final MenuBoard menuBoard;
    private final int quantity;

    public Menu(MenuBoard menuBoard, int quantity) {
        this.menuBoard = menuBoard;
        this.quantity = quantity;
    }

    public MenuBoard getMenuBoard() {
        return menuBoard;
    }

    public int getQuantity() {
        return quantity;
    }
}
