package christmas.domain;

import java.util.List;

public class Order {
    private final List<String> menuNames;
    private final List<Menu> orderedMenus;

    public Order(List<String> menuNames, List<Menu> orderedMenus) {
        this.menuNames = menuNames;
        this.orderedMenus = orderedMenus;
    }

    @Override
    public String toString() {
        return "OrderedMenu{" +
                "menuNames=" + menuNames +
                ", orderedMenus=" + orderedMenus +
                '}';
    }
}
