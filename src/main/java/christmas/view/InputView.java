package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.validation.InputValidation;
import christmas.utils.GameMessage;
import christmas.utils.Util;

public class InputView {
    public static int getVisitingDate() {
        OutputView.printMessage(GameMessage.ASK_VISITING_DATE.getMessage());
        String input = Console.readLine();
        InputValidation.validateVisitingDateInput(input);
        return Util.convertToInt(input);
    }
}
