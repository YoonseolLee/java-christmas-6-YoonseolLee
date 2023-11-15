package christmas.domain.event;

public class GiveAwayBenefit {
    private final int value;

    public GiveAwayBenefit(int value) {
        this.value = value;
    }

    public GiveAwayBenefit subtract(int amount) {
        return new GiveAwayBenefit(this.value - amount);
    }

    public int getValue() {
        return value;
    }
}
