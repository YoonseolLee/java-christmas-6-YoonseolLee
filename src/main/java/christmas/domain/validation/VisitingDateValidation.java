package christmas.domain.validation;

import christmas.utils.Constants;
import christmas.utils.ErrorMessage;

public class VisitingDateValidation {
    public static void validateVisitingDate(int day) {
        if (!isValidDayRange(day)) {
            throw new IllegalArgumentException(ErrorMessage.VISITING_DATE_OUT_OF_RANGE_ERROR.getMessage());
        }
    }

    private static boolean isValidDayRange(int input) {
        return input >= Constants.START_OF_MONTH && input <= Constants.END_OF_MONTH;
    }
}
