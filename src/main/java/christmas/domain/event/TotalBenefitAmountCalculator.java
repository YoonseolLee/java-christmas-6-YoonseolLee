package christmas.domain.event;

public class TotalBenefitAmountCalculator {
    public static int totalBenefitAmount = 0;

    public static void calculateTotalBenefitAmount(DiscountCalculator discountCalculator, EventApplier eventApplier) {
        totalBenefitAmount = discountCalculator.totalDiscount + eventApplier.getGiveawayBenefit();
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
