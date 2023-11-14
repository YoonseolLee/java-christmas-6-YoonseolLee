package christmas.controller;

import christmas.domain.DiscountCalculator;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.text.DecimalFormat;

public class MainController {
    DiscountCalculator calculator;

    public void start() {
        VisitingDate visitingDate = receiveVisitingDate();
        Order order = receiveOrder();
        printOrderedMenus(visitingDate, order);
        printTotalPriceBeforeDiscount(order);
        DiscountCalculator.calculateGiveAwayEvent(order);
        DiscountCalculator.printGiveaway();

        // 크리스마스 디데이 할인
        OutputView.printNewLine();
        OutputView.printMessage("<혜택 내역>");
        int dDayDiscountAmount = DiscountCalculator.calculateDDayDiscount(visitingDate);
        OutputView.printDdayDiscountAmount(dDayDiscountAmount);

        // 평일/주말할인
        int everyDayDiscountAmount = DiscountCalculator.calculateEveryDayDiscount(order, visitingDate);
        OutputView.printEveryDayDiscountAmount(everyDayDiscountAmount, visitingDate);

        // 특별할인
        int specialDiscountAmount = DiscountCalculator.calculateSpeicalDiscount(visitingDate);
        OutputView.printSpecialDiscountAmount(specialDiscountAmount);

        // 증정이벤트
        DiscountCalculator.printGiveawayBenefit();

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
}
