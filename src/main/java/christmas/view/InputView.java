package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.domain.MenuBoard;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.validation.OrderValidation;
import christmas.domain.validation.VisitingDateValidation;
import christmas.utils.ErrorMessage;
import christmas.utils.GameMessage;
import christmas.utils.Util;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public static Order getOrder() {
        while (true) {
            try {
                List<String> menuNames = getMenuNames();
                List<Menu> orderedMenus = getOrderedMenus(menuNames);
                return new Order(menuNames, orderedMenus);
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }

    public static List<String> getMenuNames() {
        String orderInput;
        do {
            orderInput = receiveOrderInput();
        } while (!OrderValidation.isValidOrder(orderInput));
        return convertInputToMenuNames(orderInput);
    }

    private static String receiveOrderInput() {
        OutputView.printMessage(GameMessage.ENTER_ORDER_MESSAGE.getMessage());
        try {
            return Console.readLine();
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return "";
        }
    }

    private static List<String> convertInputToMenuNames(String input) {
        return Arrays.asList(input.split(","));
    }

    public static List<Menu> getOrderedMenus(List<String> menuNames) {
        Pattern pattern = Pattern.compile("(.+)-([0-9]+)");

        return menuNames.stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new Menu(MenuBoard.valueOf(matcher.group(1)), Integer.parseInt(matcher.group(2))))
                .collect(Collectors.toList());
    }
}
