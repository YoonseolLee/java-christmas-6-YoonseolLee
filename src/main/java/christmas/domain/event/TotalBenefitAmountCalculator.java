package christmas.domain.event;

import christmas.domain.event.wrapped.TotalBenefit;

public class TotalBenefitAmountCalculator {
    private TotalBenefit totalBenefit;

    public TotalBenefitAmountCalculator() {
        this.totalBenefit = new TotalBenefit(0);
    }

    public void calculateTotalBenefitAmount(DiscountCalculator discountCalculator, EventApplier eventApplier) {
        totalBenefit =
                new TotalBenefit(discountCalculator.getTotalDiscount().getValue() + eventApplier.getGiveawayBenefit()
                        .getValue());
    }

    public TotalBenefit getTotalBenefit() {
        return totalBenefit;
    }
}
