package christmas.domain.order;

import christmas.utils.Constants;
import java.util.List;
import java.util.function.Predicate;

public class Order {
    private final List<String> menuNames;
    private final List<Menu> orderedMenus;
    private final int totalPriceBeforeDiscount;

    public Order(List<String> menuNames, List<Menu> orderedMenus) {
        this.menuNames = menuNames;
        this.orderedMenus = orderedMenus;
        this.totalPriceBeforeDiscount = calculateTotalPriceBeforeDiscount();
    }

    public List<Menu> getOrderedMenus() {
        return orderedMenus;
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public boolean hasValidTotalPriceForEvents() {
        return totalPriceBeforeDiscount >= Constants.MINIMUM_PRICE_FOR_ALL_EVENTS;
    }

    public boolean isTotalPriceAboveThreshold() {
        return totalPriceBeforeDiscount >= Constants.PRICE_THRESHOLD_FOR_GIVEAWAY_EVENT;
    }

    public int getDessertCount() {
        return getCountByMenuType(Menu::isDessert);
    }

    public int getMainCount() {
        return getCountByMenuType(Menu::isMain);
    }

    public int getTotalPriceAfterDiscount(int totalBenefitAmount) {
        return isTotalPriceAboveThreshold()
                ? totalPriceBeforeDiscount - (-totalBenefitAmount) + (25_000)
                : totalPriceBeforeDiscount - (-totalBenefitAmount);
    }

    private int calculateTotalPriceBeforeDiscount() {
        return orderedMenus.stream()
                .mapToInt(menu -> menu.getMenuBoard().getPrice() * menu.getQuantity())
                .sum();
    }

    private int getCountByMenuType(Predicate<Menu> predicate) {
        return orderedMenus.stream()
                .filter(predicate)
                .mapToInt(Menu::getQuantity)
                .sum();
    }
}
