package christmas.utils;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.order.Menu;
import christmas.domain.order.MenuBoard;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {
    public static String getUserInput(String message) {
        OutputView.printMessage(message);
        return Console.readLine();
    }

    public static final int convertToInt(String input) {
        return Integer.parseInt(input);
    }

    public static final List<String> convertInputToMenuNames(String input) {
        return Arrays.asList(input.split(","));
    }

    public static final List<Menu> getOrderedMenus(List<String> menuNames) {
        Pattern pattern = Pattern.compile("(.+)-([0-9]+)");

        return menuNames.stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new Menu(MenuBoard.valueOf(matcher.group(1)), Integer.parseInt(matcher.group(2))))
                .collect(Collectors.toList());
    }
}
