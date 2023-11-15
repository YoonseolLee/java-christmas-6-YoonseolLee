package christmas.domain.event;

import christmas.domain.order.Order;
import christmas.utils.Constants;

public class EventApplier {
    private GiveAwayBenefit giveawayBenefit;
    private ChampagneCount champagneForGiveaway;

    public EventApplier() {
        this.giveawayBenefit = new GiveAwayBenefit(0);
        this.champagneForGiveaway = new ChampagneCount(0);
    }

    public void calculateGiveAwayEvent(Order order) {
        if (order.isTotalPriceAboveThreshold() && order.hasValidTotalPriceForEvents()) {
            giveawayBenefit = giveawayBenefit.subtract(Constants.CHAMPAGNE_PRICE);
            champagneForGiveaway = champagneForGiveaway.increment();
        }
    }

    public GiveAwayBenefit getGiveawayBenefit() {
        return giveawayBenefit;
    }

    public ChampagneCount getChampagneForGiveaway() {
        return champagneForGiveaway;
    }
}
