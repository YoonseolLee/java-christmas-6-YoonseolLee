package christmas.domain.event.badge;

public class GrantedEventBadge {
    private final String badge;

    private GrantedEventBadge(String badge) {
        this.badge = badge;
    }

    public static GrantedEventBadge of(int totalBenefitAmount) {
        String badge = EventBadge.getBadgeByTotalBenefitAmount(-totalBenefitAmount);
        return new GrantedEventBadge(badge);
    }

    public String getBadge() {
        return this.badge;
    }
}


