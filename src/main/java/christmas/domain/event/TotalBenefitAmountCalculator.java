package christmas.domain.event;

public class TotalBenefitAmountCalculator {
    private int totalBenefitAmount = 0;

    public void calculateTotalBenefitAmount(DiscountCalculator discountCalculator, EventApplier eventApplier) {
        totalBenefitAmount =
                discountCalculator.getTotalDiscount().getValue() + eventApplier.getGiveawayBenefit().getValue();
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
