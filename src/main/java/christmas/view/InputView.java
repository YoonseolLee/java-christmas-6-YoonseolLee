package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitingDate;
import christmas.domain.validation.VisitingDateValidation;
import christmas.utils.GameMessage;
import christmas.utils.Util;

public class InputView {
    public static VisitingDate getVisitingDate() {
        while (true) {
            try {
                OutputView.printMessage(GameMessage.ASK_VISITING_DATE.getMessage());
                String input = Console.readLine();
                if (VisitingDateValidation.isValidDate(input)) {
                    int day = Util.convertToInt(input);
                    return new VisitingDate(day);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
