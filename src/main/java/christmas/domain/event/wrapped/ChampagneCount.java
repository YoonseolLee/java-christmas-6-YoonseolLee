package christmas.domain.event.wrapped;

public class ChampagneCount {
    private final int count;

    public ChampagneCount(int count) {
        this.count = count;
    }

    public ChampagneCount increment() {
        return new ChampagneCount(this.count + 1);
    }

    public int getCount() {
        return count;
    }
}
