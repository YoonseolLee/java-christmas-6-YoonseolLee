package christmas.domain.event;

public class Benefit {
    private final int value;

    public Benefit(int value) {
        this.value = value;
    }

    public Benefit subtract(int amount) {
        return new Benefit(this.value - amount);
    }

    public int getValue() {
        return value;
    }
}
