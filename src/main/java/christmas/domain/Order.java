package christmas.domain;

import christmas.view.OutputView;
import java.text.DecimalFormat;
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

    public void printOrderedMenus() {
        OutputView.printMessage("<주문 메뉴>");
        orderedMenus.forEach(menu ->
                OutputView.printMessage(menu.getMenuBoard().toString() + " " + menu.getQuantity() + "개"));
        OutputView.printNewLine();
    }

    public void printTotalPriceBeforeDiscount() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        OutputView.printMessage("<할인 전 총주문 금액>");
        OutputView.printMessage(formatter.format(totalPriceBeforeDiscount) + "원");
    }

}
