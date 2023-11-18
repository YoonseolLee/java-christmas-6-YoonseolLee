package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.date.VisitingDate;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.validation.OrderValidation;
import christmas.domain.validation.VisitingDateValidation;
import christmas.utils.Constants;
import christmas.utils.ErrorMessage;
import christmas.utils.GameMessage;
import christmas.utils.Util;
import java.util.List;

public class InputView {
    public static VisitingDate getVisitingDate() {
        OutputView.printMessage(GameMessage.GREETINGS_MESSAGE.getMessage());
        while (true) {
            try {
                String input = Util.getUserInput(GameMessage.ASK_VISITING_DATE.getMessage());
                validateDate(input);
                int day = Util.convertToInt(input);
                return new VisitingDate(day);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }

    private static void validateDate(String input) {
        if (!VisitingDateValidation.isValidDate(input)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR_MESSAGE.getMessage());
        }
    }

    public static Order getOrder(OrderValidation orderValidation) {
        while (true) {
            try {
                List<String> menuNames = getMenuNames(orderValidation);
                List<Menu> orderedMenus = Util.getOrderedMenus(menuNames);
                return new Order(menuNames, orderedMenus);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }

    public static List<String> getMenuNames(OrderValidation orderValidation) {
        String orderInput;
        do {
            orderInput = receiveOrderInput();
        } while (!orderValidation.isValidOrder(orderInput));
        return Util.convertInputToMenuNames(orderInput);
    }

    private static String receiveOrderInput() {
        OutputView.printMessage(GameMessage.ENTER_ORDER_MESSAGE.getMessage());
        try {
            return Console.readLine();
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return Constants.EMPTY_STRING;
        }
    }
}
