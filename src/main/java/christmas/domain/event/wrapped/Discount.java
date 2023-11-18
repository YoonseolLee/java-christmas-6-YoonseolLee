package christmas.domain.event.wrapped;

public class Discount {
    private final int value;

    public Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
