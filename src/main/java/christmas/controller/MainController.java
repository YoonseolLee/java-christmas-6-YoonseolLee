package christmas.controller;

import christmas.domain.DiscountCalculator;
import christmas.domain.EventBadge;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class MainController {
    DiscountCalculator calculator = new DiscountCalculator();

    public void start() {
        // 1. 식당 방문일 등록
        VisitingDate visitingDate = receiveVisitingDate();
        // 2. 주문메뉴와 개수 등록
        Order order = generateOrderDetails(visitingDate);
        // 3. 이벤트 대상 등록
        calculator.calculateGiveAwayEvent(order);
        OutputView.printGiveaway();
        OutputView.printMessage("<혜택 내역>");
        Map<String, Integer> discountAmounts = calculator.calculateDiscountAmount(visitingDate, order);
        printDiscountOrMessage(discountAmounts, visitingDate, order, calculator);

        // 총혜택금액
        DiscountCalculator.calculateTotalBenefitAmount();
        int totalBenefitAmount = DiscountCalculator.getTotalBenefitAmount();
        DecimalFormat formatter = new DecimalFormat("#,###");
        System.out.println("<총혜택 금액>");
        System.out.println(formatter.format(totalBenefitAmount) + "원");
        System.out.println();

        // 할인 후 예상 결제 금액
        int totalPriceAfterDiscount = order.getTotalPriceAfterDiscount(totalBenefitAmount);
        order.printTotalPriceAfterDiscount(totalPriceAfterDiscount);

        // 12월 이벤트 배지
        String eventBadge = EventBadge.getBadgeByTotalBenefitAmount(-totalBenefitAmount);
        OutputView.printNewLine();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventBadge);
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

    public void printDiscountOrMessage(Map<String, Integer> discountAmounts, VisitingDate visitingDate, Order order,
                                       DiscountCalculator calculator) {
        int dDayDiscountAmount = discountAmounts.getOrDefault("D-Day Discount Amount", 0);
        int everyDayDiscountAmount = discountAmounts.getOrDefault("Every Day Discount Amount", 0);
        int specialDiscountAmount = discountAmounts.getOrDefault("Special Discount Amount", 0);

        int totalDiscount = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;

        if (totalDiscount == 0) {
            OutputView.printMessage("없음");
        }

        if (totalDiscount != 0 && calculator.isEligibleForEvents(dDayDiscountAmount, everyDayDiscountAmount,
                specialDiscountAmount, order)) {
            OutputView.printDiscount(discountAmounts, visitingDate, calculator.getGiveawayBenefit());
        }
    }

    private void printOrderedMenus(VisitingDate visitingDate, List<Menu> orderedMenus) {
        OutputView.printEventPreview(visitingDate);
        OutputView.printOrderedMenus(orderedMenus);
    }

    private void printTotalPriceBeforeDiscount(Order order) {
        order.printTotalPriceBeforeDiscount();
    }
}
