package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitingDate;
import christmas.domain.validation.VisitingDateValidation;
import christmas.utils.ErrorMessage;
import christmas.utils.GameMessage;
import christmas.utils.Util;

public class InputView {
    public static VisitingDate getVisitingDate() {
        while (true) {
            try {
                String input = getUserInput(GameMessage.ASK_VISITING_DATE.getMessage());
                validateDate(input);
                int day = convertToInt(input);
                return new VisitingDate(day);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }

    private static String getUserInput(String message) {
        OutputView.printMessage(message);
        return Console.readLine();
    }

    private static void validateDate(String input) {
        if (!VisitingDateValidation.isValidDate(input)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR_MESSAGE.getMessage());
        }
    }

    private static int convertToInt(String input) {
        return Util.convertToInt(input);
    }


}
