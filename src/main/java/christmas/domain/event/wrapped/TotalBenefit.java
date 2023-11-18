package christmas.domain.event.wrapped;

public class TotalBenefit {
    private final int value;

    public TotalBenefit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
