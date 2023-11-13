package christmas.domain.validation;

import christmas.utils.Constants;
import christmas.utils.ErrorMessage;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class VisitingDateValidation {
    private static List<Predicate<String>> validations = Arrays.asList(
            input -> input != null && !input.trim().isEmpty(),
            input -> input.matches(Constants.REGEX_NUMERIC),
            input -> !input.startsWith("0")
    );

    public static boolean isValidDate(String input) {
        boolean isValid = validations.stream().allMatch(validation -> validation.test(input));
        if (!isValid) {
            System.out.println(OutputView.errorDateMessage());
        }
        return isValid;
    }

    public static void validateVisitingDate(int day) {
        if (!isValidDayRange(day)) {
            throw new IllegalArgumentException(ErrorMessage.VISITING_DATE_OUT_OF_RANGE_ERROR.getMessage());
        }
    }

    public static boolean isValidDayRange(int input) {
        return input >= Constants.START_OF_MONTH && input <= Constants.END_OF_MONTH;
    }
}
