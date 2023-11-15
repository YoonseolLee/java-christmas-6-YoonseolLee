package christmas.controller;

import static christmas.domain.event.DiscountCalculator.totalBenefitAmount;

import christmas.domain.date.VisitingDate;
import christmas.domain.event.DiscountCalculator;
import christmas.domain.event.EventApplier;
import christmas.domain.event.EventBadge;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class MainController {
    DiscountCalculator calculator = new DiscountCalculator();
    EventApplier eventApplier = new EventApplier();

    public void start() {
        // 1. 식당 방문일 등록
        VisitingDate visitingDate = receiveVisitingDate();
        // 2. 주문메뉴와 개수 등록
        Order order = generateOrderDetails(visitingDate);
        // 3. 증정이벤트 적용
        applyGiveawayEvent(eventApplier, order);
        // 4. 할인 적용
        applyDiscounts(calculator, visitingDate, order, eventApplier);
        // 5. 총혜택금액 적용
        int totalBenefitAmount = applyTotalBenefitAmount();
        // 6. 할인 후 예상 결제 금액
        applyTotalPriceAfterDiscount(totalBenefitAmount, order);
        // 7. 12월 이벤트 배지
        applyEventBadge();
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

    public void applyGiveawayEvent(EventApplier eventApplier, Order order) {
        eventApplier.calculateGiveAwayEvent(order);
        OutputView.printGiveaway(eventApplier);
    }

    public void applyDiscounts(DiscountCalculator calculator, VisitingDate visitingDate, Order order,
                               EventApplier eventApplier) {
        OutputView.printMessage("<혜택 내역>");
        Map<String, Integer> discountAmounts = calculator.calculateDiscountAmount(visitingDate, order);
        printDiscountOrMessage(discountAmounts, visitingDate, order, calculator, eventApplier);
    }


    public void printDiscountOrMessage(Map<String, Integer> discountAmounts, VisitingDate visitingDate, Order order,
                                       DiscountCalculator calculator, EventApplier eventApplier) {
        int dDayDiscountAmount = discountAmounts.getOrDefault("D-Day Discount Amount", 0);
        int everyDayDiscountAmount = discountAmounts.getOrDefault("Every Day Discount Amount", 0);
        int specialDiscountAmount = discountAmounts.getOrDefault("Special Discount Amount", 0);

        int totalDiscount = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;

        if (totalDiscount == 0) {
            OutputView.printMessage("없음");
            OutputView.printNewLine();
        }

        if (totalDiscount != 0 && calculator.isEligibleForEvents(dDayDiscountAmount, everyDayDiscountAmount,
                specialDiscountAmount, order)) {
            OutputView.printDiscount(discountAmounts, visitingDate, eventApplier.getGiveawayBenefit());
        }
    }

    public int applyTotalBenefitAmount() {
        DiscountCalculator.calculateTotalBenefitAmount(eventApplier);
        int totalBenefitAmount = DiscountCalculator.getTotalBenefitAmount();
        OutputView.printTotalBenefitAmount(totalBenefitAmount);
        return totalBenefitAmount;
    }

    public void applyTotalPriceAfterDiscount(int totalBenefitAmount, Order order) {
        int totalPriceAfterDiscount = order.getTotalPriceAfterDiscount(totalBenefitAmount);
        OutputView.printTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }

    public void applyEventBadge() {
        String eventBadge = EventBadge.getBadgeByTotalBenefitAmount(-totalBenefitAmount);
        OutputView.printEventBadge(eventBadge);
    }


    private void printOrderedMenus(VisitingDate visitingDate, List<Menu> orderedMenus) {
        OutputView.printEventPreview(visitingDate);
        OutputView.printOrderedMenus(orderedMenus);
    }

    private void printTotalPriceBeforeDiscount(Order order) {
        order.printTotalPriceBeforeDiscount();
    }
}
