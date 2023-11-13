package christmas.domain;

import java.util.List;

public class Order {
    private final List<String> menuNames;
    private final List<Menu> orderedMenus;
    private final int totalPriceBeforeDiscount;

//    [해산물파스타-2, 레드와인-1, 초코케이크-1]
//    [Menu{menuBoard=해산물파스타, quantity=2}, Menu{menuBoard=레드와인, quantity=1}, Menu{menuBoard=초코케이크, quantity=1}]

    public Order(List<String> menuNames, List<Menu> orderedMenus) {
        this.menuNames = menuNames;
        this.orderedMenus = orderedMenus;
        this.totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount();
    }

    private int calculateTotalPriceBeforeDiscount() {
        return orderedMenus.stream()
                .mapToInt(menu -> menu.getMenuBoard().getPrice() * menu.getQuantity())
                .sum();
    }
}
