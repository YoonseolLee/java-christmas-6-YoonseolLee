package christmas.domain.event;

import christmas.domain.order.Order;
import christmas.utils.Constants;

public class EventApplier {
    private int giveawayBenefit = 0;
    private int champagneForGiveaway = 0;

    public void calculateGiveAwayEvent(Order order) {
        if (order.isTotalPriceAboveThreshold() && order.hasValidTotalPriceForEvents()) {
            giveawayBenefit -= Constants.CHAMPAGNE_PRICE;
            champagneForGiveaway++;
        }
    }

    public int getGiveawayBenefit() {
        return giveawayBenefit;
    }

    public int getChampagneForGiveaway() {
        return champagneForGiveaway;
    }
}
