package christmas.domain.validation;

import christmas.domain.MenuBoard;
import christmas.utils.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class OrderValidation {
    public static boolean isValidOrder(String input) {
        List<String> menuNames = List.of(input.split(","));
        if (hasValidFormat(menuNames)
                && containsOnlyExistingMenus(menuNames)
                && hasValidMenuCount(menuNames)
                && hasNoDuplicateMenus(menuNames)
                && includesNonBeverageItem(menuNames)
                && doesNotExceedMaxMenuCount(menuNames)) {
            return true;
        }
        throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR_MESSAGE.getMessage());
    }


    public static boolean hasValidFormat(List<String> menuNames) {
        return menuNames.stream()
                .allMatch(s -> s.split("-").length == 2);
    }

    public static boolean containsOnlyExistingMenus(List<String> menuNames) {
        return menuNames.stream()
                .allMatch(s -> {
                    try {
                        MenuBoard.valueOf(s.split("-")[0]);
                        return true;
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                });
    }

    public static boolean hasValidMenuCount(List<String> menuNames) {
        return menuNames.stream()
                .allMatch(s -> Integer.parseInt(s.split("-")[1]) >= 1);
    }

    public static boolean hasNoDuplicateMenus(List<String> menuNames) {
        List<String> menus = menuNames.stream()
                .map(s -> s.split("-")[0])
                .collect(Collectors.toList());
        return new HashSet<>(menus).size() == menus.size();
    }

    public static boolean includesNonBeverageItem(List<String> menuNames) {
        return menuNames.stream()
                .anyMatch(s -> !MenuBoard.valueOf(s.split("-")[0]).getSort().equals("Beverage"));
    }

    public static boolean doesNotExceedMaxMenuCount(List<String> menuNames) {
        int menuCount = menuNames.stream()
                .mapToInt(s -> Integer.parseInt(s.split("-")[1]))
                .sum();
        return menuCount <= 20;
    }
}
