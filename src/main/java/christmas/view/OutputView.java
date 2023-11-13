package christmas.view;

import christmas.domain.VisitingDate;
import christmas.utils.GameMessage;

public class OutputView {
    public static void printGreetings() {
        printMessage(GameMessage.GREETINGS_MESSAGE.getMessage());
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

    public static void printGiveaways() {
        OutputView.printNewLine();
        OutputView.printMessage("<증정 메뉴>");
        OutputView.printMessage("샴페인 n개");
    }

    public static void printEveryDayDiscountAmount(int everyDayDiscountAmount, VisitingDate visitingDate) {
        // 가격에 쉼표 붙이기
        if (visitingDate.isWeekend()) {
            printMessage("주말 할인: -" + everyDayDiscountAmount + "원");
        }
        if (!visitingDate.isWeekend()) {
            printMessage("평일 할인: -" + everyDayDiscountAmount + "원");
        }
    }

    public static void printDdayDiscountAmount(int dDayDiscountAmount) {
        printMessage("크리스마스 디데이 할인: -" + dDayDiscountAmount + "원");
    }

    public static void printSpecialDiscountAmount(int specialDiscountAmount) {
        printMessage("특별 할인: -" + specialDiscountAmount + "원");
    }

//        OutputView.printMessage("특별 할인: ");
//        OutputView.printMessage("증정 이벤트: ");
}


