package christmas.domain.order;

import static christmas.domain.order.MenuSort.APPETIZER;
import static christmas.domain.order.MenuSort.BEVERAGE;
import static christmas.domain.order.MenuSort.DESSERT;
import static christmas.domain.order.MenuSort.MAIN;

public enum MenuBoard {
    양송이수프(APPETIZER, 6_000), 타파스(APPETIZER, 5_500), 시저샐러드(APPETIZER, 8_000),
    티본스테이크(MAIN, 55_000), 바비큐립(MAIN, 54_000), 해산물파스타(MAIN, 35_000), 크리스마스파스타(MAIN, 25_000),
    초코케이크(DESSERT, 15_000), 아이스크림(DESSERT, 5_000),
    제로콜라(BEVERAGE, 3_000), 레드와인(BEVERAGE, 60_000), 샴페인(BEVERAGE, 25_000);

    private final MenuSort sort;
    private final int price;

    MenuBoard(MenuSort sort, int price) {
        this.sort = sort;
        this.price = price;
    }

    public MenuSort getSort() {
        return sort;
    }

    public int getPrice() {
        return price;
    }
}
