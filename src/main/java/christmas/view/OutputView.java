package christmas.view;

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
}
