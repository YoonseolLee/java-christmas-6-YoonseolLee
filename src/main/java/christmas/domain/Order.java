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

    public boolean hasValidTotalPriceForEvents() {
        return totalPriceBeforeDiscount >= 10000;
    }

    private int calculateTotalPriceBeforeDiscount() {
        return orderedMenus.stream()
                .mapToInt(menu -> menu.getMenuBoard().getPrice() * menu.getQuantity())
                .sum();
    }

    public List<Menu> getOrderedMenus() {
        return orderedMenus;
    }

    public void printTotalPriceBeforeDiscount() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        OutputView.printMessage("<할인 전 총주문 금액>");
        OutputView.printMessage(formatter.format(totalPriceBeforeDiscount) + "원");
    }


    public int getDessertCount() {
        int dessertCount = 0;
        for (Menu menu : orderedMenus) {
            if (menu.isDessert()) {
                dessertCount += menu.getQuantity();
            }
        }
        return dessertCount;
    }

    public int getMainCount() {
        int mainCount = 0;
        for (Menu menu : orderedMenus) {
            if (menu.isMain()) {
                mainCount += menu.getQuantity();
            }
        }
        return mainCount;
    }

    public boolean isTotalPriceAboveThreshold() {
        return totalPriceBeforeDiscount >= PRICE_THRESHOLD;
    }

    public int getTotalPriceAfterDiscount(int totalBenefitAmount) {
        int totalPriceAfterDiscount = 0;
        if (isTotalPriceAboveThreshold()) {
            totalPriceAfterDiscount = totalPriceBeforeDiscount - (-totalBenefitAmount) + (25_000);
        }
        if (!isTotalPriceAboveThreshold()) {
            totalPriceAfterDiscount = totalPriceBeforeDiscount - (-totalBenefitAmount);
        }
        return totalPriceAfterDiscount;
    }

    public void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        OutputView.printMessage("<할인 후 예상 결제 금액>");
        OutputView.printMessage(formatter.format(totalPriceAfterDiscount) + "원");
    }
}
