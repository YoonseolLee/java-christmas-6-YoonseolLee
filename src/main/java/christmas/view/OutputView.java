package christmas.view;

import christmas.domain.date.VisitingDate;
import christmas.domain.event.EventApplier;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.utils.Constants;
import christmas.utils.GameMessage;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printEventPreview(VisitingDate visitingDate) {
        printFormattedMessage(GameMessage.BENEFIT_PREVIEW_ON_VISITING_DATE_MESSAGE.getMessage(),
                visitingDate.getDayOfMonth());
        printNewLine();
    }

    public static void printOrderedMenus(List<Menu> orderedMenus) {
        printMessage(GameMessage.ORDERED_MENU.getMessage());
        orderedMenus.forEach(menu ->
                printFormattedMessage("%s %d개%n", menu.getMenuBoard(), menu.getQuantity()));
        printNewLine();
    }

    public static void printTotalPriceBeforeDiscount(Order order) {
        DecimalFormat formatter = new DecimalFormat(Constants.NUMBER_FORMAT_PATTERN);
        printMessage(GameMessage.TOTAL_PRICE_BEFORE_DISCOUNT.getMessage());
        printMessage(formatter.format(order.getTotalPriceBeforeDiscount()) + "원");
    }

    public static void printGiveaway(EventApplier eventApplier) {
        int champagneForGiveaway = eventApplier.getChampagneForGiveaway().getCount();

        if (champagneForGiveaway == 0) {
            printNewLine();
            printMessage(GameMessage.GIVEAWAY_MENU.getMessage());
            printMessage(GameMessage.NONE_MESSAGE.getMessage());
            printNewLine();
            return;
        }

        if (champagneForGiveaway > 0) {
            printGiveaways(champagneForGiveaway);
            printNewLine();
        }
    }

    public static void printGiveaways(int champagneForGiveaway) {
        printNewLine();
        printMessage(GameMessage.GIVEAWAY_MENU.getMessage());
        printFormattedMessage("샴페인 %d개%n", champagneForGiveaway);
    }

    public static void printDiscount(Map<String, Integer> discountAmounts, VisitingDate visitingDate,
                                     int giveawayBenefit) {
        int dDayDiscountAmount = discountAmounts.getOrDefault("D-Day Discount Amount", 0);
        int everyDayDiscountAmount = discountAmounts.getOrDefault("Every Day Discount Amount", 0);
        int specialDiscountAmount = discountAmounts.getOrDefault("Special Discount Amount", 0);
        printDdayDiscountAmount(dDayDiscountAmount);
        printEveryDayDiscountAmount(everyDayDiscountAmount, visitingDate);
        printSpecialDiscountAmount(specialDiscountAmount);
        printGiveawayBenefit(giveawayBenefit);
    }

    public static void printDdayDiscountAmount(int dDayDiscountAmount) {
        printFormattedMessage(GameMessage.CHRISTMAS_DDAY_DISCOUNT.getMessage(), formatCurrency(dDayDiscountAmount));
    }

    public static void printEveryDayDiscountAmount(int everyDayDiscountAmount, VisitingDate visitingDate) {
        DecimalFormat formatter = new DecimalFormat(Constants.NUMBER_FORMAT_PATTERN);
        String formattedDiscount = formatter.format(everyDayDiscountAmount) + "원";

        if (visitingDate.isWeekend()) {
            printFormattedMessage(GameMessage.WEEKND_DISCOUNT.getMessage(), formattedDiscount);
        }
        if (!visitingDate.isWeekend()) {
            printFormattedMessage(GameMessage.WEEKDAY_DISCOUNT.getMessage(), formattedDiscount);
        }
    }

    public static void printSpecialDiscountAmount(int specialDiscountAmount) {
        printFormattedMessage(GameMessage.SPECIAL_DISCOUNT.getMessage(), formatCurrency(specialDiscountAmount));
    }

    public static void printGiveawayBenefit(int giveawayBenefit) {
        DecimalFormat formatter = new DecimalFormat(Constants.NUMBER_FORMAT_PATTERN);
        String formattedBenefit = formatter.format(giveawayBenefit);
        printFormattedMessage(GameMessage.GIVEAWAY_EVENT.getMessage(), formattedBenefit);
        printNewLine();
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        DecimalFormat formatter = new DecimalFormat(Constants.NUMBER_FORMAT_PATTERN);
        printMessage(GameMessage.TOTAL_BENEFIT_AMOUNT.getMessage());
        printMessage(formatter.format(totalBenefitAmount) + "원");
        printNewLine();
    }

    public static void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        DecimalFormat formatter = new DecimalFormat(Constants.NUMBER_FORMAT_PATTERN);
        printMessage(GameMessage.ESTIMATED_PRICE_AFTER_DISCOUNT.getMessage());
        printMessage(formatter.format(totalPriceAfterDiscount) + "원");
    }

    public static void printEventBadge(String eventBadge) {
        printNewLine();
        printMessage(GameMessage.DECEMBER_EVENT_BADGE.getMessage());
        printMessage(eventBadge);
    }

    private static String formatCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat(Constants.NUMBER_FORMAT_PATTERN);
        return formatter.format(amount);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printFormattedMessage(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void printNewLine() {
        System.out.println();
    }
}
