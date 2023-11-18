package christmas.domain.order;

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

    public boolean isDessert() {
        return menuBoard.getSort().equals(MenuSort.DESSERT);
    }

    public boolean isMain() {
        return menuBoard.getSort().equals(MenuSort.MAIN);
    }
}
