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

public class MainController {
    DiscountCalculator calculator;

    public void start() {
        // 1. 식당 방문일 등록 (very good. 생성자도 등록됨. 추후 의존성 필요)
        VisitingDate visitingDate = receiveVisitingDate();
        Order order = receiveOrder();
        printOrderedMenus(visitingDate, order);
        printTotalPriceBeforeDiscount(order);
        DiscountCalculator.calculateGiveAwayEvent(order);
        DiscountCalculator.printGiveaway();
        OutputView.printNewLine();
        OutputView.printMessage("<혜택 내역>");

        printDiscountAmount(visitingDate, order, calculator);

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

    private Order receiveOrder() {
        Order order = InputView.getOrder();
        return order;
    }

    private void printOrderedMenus(VisitingDate visitingDate, Order order) {
        visitingDate.printEventPreview();
        order.printOrderedMenus();
    }

    private void printTotalPriceBeforeDiscount(Order order) {
        order.printTotalPriceBeforeDiscount();
    }

    private void printDiscountAmount(VisitingDate visitingDate, Order order, DiscountCalculator calculator) {
        int dDayDiscountAmount = visitingDate.calculateDDayDiscountAmount(order);
        int everyDayDiscountAmount = calculator.calculateEveryDayDiscount(order, visitingDate);
        int specialDiscountAmount = visitingDate.calculateSpecialDiscountAmount(order);

        if (!isEligibleForEvents(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount, order)) {
            System.out.println("없음");
            OutputView.printNewLine();
            return;
        }
        DiscountCalculator.updateTotalDiscount(dDayDiscountAmount, everyDayDiscountAmount, specialDiscountAmount);
        OutputView.printDdayDiscountAmount(dDayDiscountAmount);
        OutputView.printEveryDayDiscountAmount(everyDayDiscountAmount, visitingDate);
        OutputView.printSpecialDiscountAmount(specialDiscountAmount);
        DiscountCalculator.printGiveawayBenefit();
    }

    private boolean isEligibleForEvents(int dDayDiscountAmount, int everyDayDiscountAmount, int specialDiscountAmount,
                                        Order order) {
        int totalSum = dDayDiscountAmount + everyDayDiscountAmount + specialDiscountAmount;
        return totalSum < 0 && order.hasValidTotalPriceForEvents();
    }
}
