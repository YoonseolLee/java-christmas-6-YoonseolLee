package christmas.domain.order;

public enum MenuBoard {
    양송이수프("Appetizer", 6_000), 타파스("Appetizer", 5_500), 시저샐러드("Appetizer", 8_000),
    티본스테이크("Main", 55_000), 바비큐립("Main", 54_000), 해산물파스타("Main", 35_000), 크리스마스파스타("Main", 25_000),
    초코케이크("Dessert", 15_000), 아이스크림("Dessert", 5_000),
    제로콜라("Beverage", 3_000), 레드와인("Beverage", 60_000), 샴페인("Beverage", 25_000);

    private final String sort;
    private final int price;

    MenuBoard(String sort, int price) {
        this.sort = sort;
        this.price = price;
    }

    public String getSort() {
        return sort;
    }

    public int getPrice() {
        return price;
    }
}
