package christmas.controller;

import christmas.domain.date.VisitingDate;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.EventApplier;
import christmas.domain.event.GrantedEventBadge;
import christmas.domain.event.TotalBenefitAmountCalculator;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.utils.GameMessage;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class MainController {
    DiscountCalculator discountCalculator = new DiscountCalculator();
    EventApplier eventApplier = new EventApplier();
    TotalBenefitAmountCalculator totalBenefitAmountCalculator = new TotalBenefitAmountCalculator();

    public void start() {
        // 1. 식당 방문일 등록
        VisitingDate visitingDate = receiveVisitingDate();
        // 2. 주문메뉴와 개수 등록
        Order order = generateOrderDetails(visitingDate);
        // 3. 증정이벤트 적용
        applyGiveawayEvent(eventApplier, order);
        // 4. 할인 적용
        applyDiscounts(discountCalculator, visitingDate, order, eventApplier);
        // 5. 총혜택금액 적용
        int totalBenefitAmount = applyTotalBenefitAmount(totalBenefitAmountCalculator, discountCalculator);
        // 6. 할인 후 예상 결제 금액
        applyTotalPriceAfterDiscount(totalBenefitAmount, order);
        // 7. 12월 이벤트 배지
        GrantedEventBadge badge = applyEventBadge(totalBenefitAmount);
    }

    private VisitingDate receiveVisitingDate() {
        VisitingDate visitingDate = InputView.getVisitingDate();
        return visitingDate;
    }

    private Order generateOrderDetails(VisitingDate visitingDate) {
        Order order = receiveOrder();
        printOrderedMenus(visitingDate, order.getOrderedMenus());
        printTotalPriceBeforeDiscount(order);
        return order;
    }

    private Order receiveOrder() {
        Order order = InputView.getOrder();
        return order;
    }

    private void applyGiveawayEvent(EventApplier eventApplier, Order order) {
        eventApplier.calculateGiveAwayEvent(order);
        OutputView.printGiveaway(eventApplier);
    }

    private void applyDiscounts(DiscountCalculator calculator, VisitingDate visitingDate, Order order,
                                EventApplier eventApplier) {
        OutputView.printMessage(GameMessage.BENEFIT_DETAILS.getMessage());
        Map<String, Integer> discountAmounts = calculator.calculateDiscountAmount(visitingDate, order);
        printDiscountOrMessage(discountAmounts, visitingDate, order, calculator, eventApplier);
    }


    private void printDiscountOrMessage(Map<String, Integer> discountAmounts, VisitingDate visitingDate, Order order,
                                        DiscountCalculator calculator, EventApplier eventApplier) {
        int dDayDiscountAmount = discountAmounts.getOrDefault("D-Day Discount Amount", 0);
        int everyDayDiscountAmount = discountAmounts.getOrDefault("Every Day Discount Amount", 0);
        int specialDiscountAmount = discountAmounts.getOrDefault("Special Discount Amount", 0);

        int totalDiscount = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;

        if (totalDiscount == 0) {
            OutputView.printMessage(GameMessage.NONE_MESSAGE.getMessage());
            OutputView.printNewLine();
        }

        if (totalDiscount != 0 && calculator.isEligibleForEvents(dDayDiscountAmount, everyDayDiscountAmount,
                specialDiscountAmount, order)) {
            OutputView.printDiscount(discountAmounts, visitingDate, eventApplier.getGiveawayBenefit());
        }
    }

    private int applyTotalBenefitAmount(TotalBenefitAmountCalculator totalBenefitAmountCalculator,
                                        DiscountCalculator discountCalculator) {
        totalBenefitAmountCalculator.calculateTotalBenefitAmount(discountCalculator, eventApplier);
        int totalBenefitAmount = totalBenefitAmountCalculator.getTotalBenefitAmount();
        OutputView.printTotalBenefitAmount(totalBenefitAmount);
        return totalBenefitAmount;
    }

    private void applyTotalPriceAfterDiscount(int totalBenefitAmount, Order order) {
        int totalPriceAfterDiscount = order.getTotalPriceAfterDiscount(totalBenefitAmount);
        OutputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }

    private GrantedEventBadge applyEventBadge(int totalBenefitAmount) {
        GrantedEventBadge grantedEventBadge = GrantedEventBadge.of(totalBenefitAmount);
        OutputView.printEventBadge(grantedEventBadge.getBadge());
        return grantedEventBadge;
    }


    private void printOrderedMenus(VisitingDate visitingDate, List<Menu> orderedMenus) {
        OutputView.printEventPreview(visitingDate);
        OutputView.printOrderedMenus(orderedMenus);
    }

    private void printTotalPriceBeforeDiscount(Order order) {
        OutputView.printTotalPriceBeforeDiscount(order);
    }
}
