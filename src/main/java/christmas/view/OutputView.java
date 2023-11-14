package christmas.view;

import christmas.domain.DiscountCalculator;
import christmas.domain.Menu;
import christmas.domain.VisitingDate;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

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

    public static void printGiveaway() {
        int champagneForGiveaway = DiscountCalculator.getChampagneForGiveaway();

        if (champagneForGiveaway == 0) {
            printNewLine();
            printMessage("<증정 메뉴>");
            System.out.println("없음");
            OutputView.printNewLine();
            return;
        }

        if (champagneForGiveaway > 0) {
            printGiveaways(champagneForGiveaway);
            OutputView.printNewLine();
        }
    }

    public static void printGiveaways(int champagneForGiveaway) {
        printNewLine();
        printMessage("<증정 메뉴>");
        printFormattedMessage("샴페인 %d개%n", champagneForGiveaway);
    }

    public static void printDiscount(Map<String, Integer> discountAmounts, VisitingDate visitingDate,
                                     int giveawayBenefit) {
        int dDayDiscountAmount = discountAmounts.getOrDefault("D-Day Discount Amount", 0);
        int everyDayDiscountAmount = discountAmounts.getOrDefault("Every Day Discount Amount", 0);
        int specialDiscountAmount = discountAmounts.getOrDefault("Special Discount Amount", 0);

        if (dDayDiscountAmount != 0) {
            printDdayDiscountAmount(dDayDiscountAmount);
        }
        if (everyDayDiscountAmount != 0) {
            printEveryDayDiscountAmount(everyDayDiscountAmount, visitingDate);
        }
        if (specialDiscountAmount != 0) {
            printSpecialDiscountAmount(specialDiscountAmount);
        }
        if (giveawayBenefit != 0) {
            printGiveawayBenefit(giveawayBenefit);
        }
    }

    public static void printDdayDiscountAmount(int dDayDiscountAmount) {
        printFormattedMessage("크리스마스 디데이 할인: %s원%n", formatCurrency(dDayDiscountAmount));
    }

    public static void printEveryDayDiscountAmount(int everyDayDiscountAmount, VisitingDate visitingDate) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedDiscount = formatter.format(everyDayDiscountAmount) + "원";

        if (visitingDate.isWeekend()) {
            printFormattedMessage("주말 할인: %s%n", formattedDiscount);
        }
        if (!visitingDate.isWeekend()) {
            printFormattedMessage("평일 할인: %s%n", formattedDiscount);
        }
    }

    public static void printSpecialDiscountAmount(int specialDiscountAmount) {
        printFormattedMessage("특별 할인: %s원%n", formatCurrency(specialDiscountAmount));
    }

    public static void printGiveawayBenefit(int giveawayBenefit) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedBenefit = formatter.format(giveawayBenefit);
        printFormattedMessage("증정 이벤트: %s원%n", formattedBenefit);
        OutputView.printNewLine();
    }

    ///////////////// 일단 여기까지
    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        OutputView.printNewLine();
        printFormattedMessage("<총 혜택 금액>%n%s원%n", formatCurrency(totalBenefitAmount));
    }

    private static String formatCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printFormattedMessage(String format, Object... args) {
        System.out.printf(format, args);
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
}


