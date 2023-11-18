package christmas.domain.validation;

import christmas.utils.Constants;
import christmas.utils.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class VisitingDateValidation {
    private static List<Predicate<String>> validations = Arrays.asList(
            input -> input != null && !input.trim().isEmpty(),
            input -> input.matches(Constants.REGEX_NUMERIC),
            input -> !input.startsWith("0"),
            VisitingDateValidation::isPositiveInteger,
            input -> isWithinRange(input, Constants.START_OF_MONTH, Constants.END_OF_MONTH)
    );

    public static boolean isValidDate(String input) {
        boolean isValid = validations.stream().allMatch(validation -> validation.test(input));
        if (!isValid) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR_MESSAGE.getMessage());
        }
        return isValid;
    }

    private static boolean isPositiveInteger(String input) {
        try {
            int value = Integer.parseInt(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isWithinRange(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
