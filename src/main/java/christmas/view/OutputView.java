package christmas.view;

import christmas.domain.VisitingDate;
import christmas.utils.GameMessage;
import java.text.DecimalFormat;

public class OutputView {
    public static void printEventPreview(VisitingDate visitingDate) {
        printMessage("12월 " + visitingDate.getDayOfMonth() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        printNewLine();
    }

    public static void printOrderedMenus(List<Menu> orderedMenus) {
        printMessage("<주문 메뉴>");
        orderedMenus.forEach(menu ->
                printMessage(menu.getMenuBoard().toString() + " " + menu.getQuantity() + "개"));
        printNewLine();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printMessageWithoutNewLine(String message) {
        System.out.print(message);
    }

    public static void printException(Exception exception) {
        printMessage(exception.getMessage());
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printGiveaways(int champagneCount) {
        OutputView.printNewLine();
        OutputView.printMessage("<증정 메뉴>");
        System.out.printf("샴페인 %d개%n", champagneCount);
    }

    public static void printGiveawayBenefit(int giveawayBenefit) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedBenefit = formatter.format(giveawayBenefit);
        System.out.printf("증정 이벤트: %s원%n", formattedBenefit);
        OutputView.printNewLine();
    }


    public static void printEveryDayDiscountAmount(int everyDayDiscountAmount, VisitingDate visitingDate) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedDiscount = formatter.format(everyDayDiscountAmount) + "원";

        if (visitingDate.isWeekend()) {
            System.out.printf("주말 할인: %s%n", formattedDiscount);
        }
        if (!visitingDate.isWeekend()) {
            System.out.printf("평일 할인: %s%n", formattedDiscount);
        }
    }

    public static void printDdayDiscountAmount(int dDayDiscountAmount) {
        System.out.printf("크리스마스 디데이 할인: %s원%n", formatCurrency(dDayDiscountAmount));
    }

    public static void printSpecialDiscountAmount(int specialDiscountAmount) {
        System.out.printf("특별 할인: %s원%n", formatCurrency(specialDiscountAmount));
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        OutputView.printNewLine();
        System.out.printf("<총 혜택 금액>%n%s원%n", formatCurrency(totalBenefitAmount));
    }

    private static String formatCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }
}


