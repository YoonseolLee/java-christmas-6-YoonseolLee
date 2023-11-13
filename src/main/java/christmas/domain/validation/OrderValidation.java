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
                && hasNoExtraSpaces(menuNames)
                && hasValidQuantityFormat(menuNames)
                && containsOnlyExistingMenus(menuNames)
                && hasValidMenuCount(menuNames)
                && hasNoDuplicateMenus(menuNames)
                && includesNonBeverageItem(menuNames)
                && doesNotExceedMaxMenuCount(menuNames)) {
            return true;
        }
        throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR_MESSAGE.getMessage());
    }

    private static boolean hasValidFormat(List<String> menuNames) {
        return menuNames.stream()
                .allMatch(s -> s.split("-").length == 2);
    }

    private static boolean hasNoExtraSpaces(List<String> menuNames) {
        return menuNames.stream()
                .noneMatch(s -> s.contains(" "));
    }

    private static boolean hasValidQuantityFormat(List<String> menuNames) {
        return menuNames.stream()
                .allMatch(s -> {
                    String quantity = s.split("-")[1];
                    return quantity.matches("\\d+") && Integer.parseInt(quantity) >= 1
                            && Integer.parseInt(quantity) <= 20;
                });
    }

    private static boolean containsOnlyExistingMenus(List<String> menuNames) {
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

    private static boolean hasValidMenuCount(List<String> menuNames) {
        return menuNames.stream()
                .allMatch(s -> Integer.parseInt(s.split("-")[1]) >= 1);
    }

    private static boolean hasNoDuplicateMenus(List<String> menuNames) {
        List<String> menus = menuNames.stream()
                .map(s -> s.split("-")[0])
                .collect(Collectors.toList());
        return new HashSet<>(menus).size() == menus.size();
    }

    private static boolean includesNonBeverageItem(List<String> menuNames) {
        return menuNames.stream()
                .anyMatch(s -> !MenuBoard.valueOf(s.split("-")[0]).getSort().equals("Beverage"));
    }

    private static boolean doesNotExceedMaxMenuCount(List<String> menuNames) {
        int menuCount = menuNames.stream()
                .mapToInt(s -> Integer.parseInt(s.split("-")[1]))
                .sum();
        return menuCount <= 20;
    }
}
