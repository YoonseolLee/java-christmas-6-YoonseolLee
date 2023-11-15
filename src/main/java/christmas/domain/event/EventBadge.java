package christmas.domain.event;

public enum EventBadge {
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int threshold;
    private final String badge;

    EventBadge(int threshold, String badge) {
        this.threshold = threshold;
        this.badge = badge;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getBadge() {
        return badge;
    }

    public static String getBadgeByTotalBenefitAmount(int totalBenefitAmount) {
        if (totalBenefitAmount >= SANTA.getThreshold()) {
            return SANTA.getBadge();
        }
        if (totalBenefitAmount >= TREE.getThreshold()) {
            return TREE.getBadge();
        }
        if (totalBenefitAmount >= STAR.getThreshold()) {
            return STAR.getBadge();
        }
        return "없음";
    }
}
