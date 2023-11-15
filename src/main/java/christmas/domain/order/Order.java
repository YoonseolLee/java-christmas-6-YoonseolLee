package christmas.domain.order;

import christmas.utils.Constants;
import java.util.List;
import java.util.function.Predicate;

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
        return totalPriceBeforeDiscount >= Constants.MINIMUM_PRICE_FOR_ALL_EVENTS;
    }

    private int calculateTotalPriceBeforeDiscount() {
        return orderedMenus.stream()
                .mapToInt(menu -> menu.getMenuBoard().getPrice() * menu.getQuantity())
                .sum();
    }

    public List<Menu> getOrderedMenus() {
        return orderedMenus;
    }

    private int getCountByMenuType(Predicate<Menu> predicate) {
        return orderedMenus.stream()
                .filter(predicate)
                .mapToInt(Menu::getQuantity)
                .sum();
    }

    public int getDessertCount() {
        return getCountByMenuType(Menu::isDessert);
    }

    public int getMainCount() {
        return getCountByMenuType(Menu::isMain);
    }

    public boolean isTotalPriceAboveThreshold() {
        return totalPriceBeforeDiscount >= Constants.PRICE_THRESHOLD_FOR_GIVEAWAY_EVENT;
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

    public List<String> getMenuNames() {
        return menuNames;
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }
}
