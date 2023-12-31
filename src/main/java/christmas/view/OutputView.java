package christmas.view;

import christmas.dto.BadgeDto;
import christmas.dto.DiscountsDto;
import christmas.dto.GiveawayMenuDto;
import christmas.dto.OrderMenusDto;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class OutputView {

    public void printStartMessage() {
        System.out.println(EventPlannerMessage.START_MESSAGE);
    }

    public void printEventBenefitsMessage(LocalDate visitDate) {
        String body = DisplayFormatter.displayVisitDate(visitDate);
        System.out.printf(EventPlannerMessage.EVENT_BENEFITS_MESSAGE, body);
        printLineSeparator();
    }

    public void printOrderMenus(OrderMenusDto orderMenus) {
        String body = orderMenus.orderMenus()
                .stream()
                .map(DisplayFormatter::displayOrderMenu)
                .collect(Collectors.joining(System.lineSeparator()));
        printTitleAndBody(EventPlannerMessage.ORDER_MENU_TITLE, body);
    }

    public void printTotalOrderAmount(int totalOrderAmount) {
        String body = DisplayFormatter.displayOrderAmount(totalOrderAmount);
        printTitleAndBody(EventPlannerMessage.TOTAL_ORDER_AMOUNT_TITLE, body);
    }

    public void printGiveawayMenuNone() {
        printNone(EventPlannerMessage.GIVEAWAY_TITLE);
    }

    public void printGiveawayMenu(GiveawayMenuDto giveawayMenu) {
        String body = DisplayFormatter.displayGiveawayMenu(giveawayMenu);
        printTitleAndBody(EventPlannerMessage.GIVEAWAY_TITLE, body);
    }

    public void printDiscounts(DiscountsDto discountsDto) {
        String body = discountsDto.discounts()
                .stream()
                .map(DisplayFormatter::displayDiscount)
                .collect(Collectors.joining(System.lineSeparator()));
        printTitleAndBody(EventPlannerMessage.DISCOUNTS_TITLE, body);
    }

    public void printDiscountsNone() {
        printNone(EventPlannerMessage.DISCOUNTS_TITLE);
    }

    public void printTotalBenefitsAmount(int totalBenefitsAmount) {
        String body = DisplayFormatter.displayBenefitsAmount(totalBenefitsAmount);
        printTitleAndBody(EventPlannerMessage.TOTAL_BENEFITS_AMOUNT_MESSAGE, body);
    }

    public void printPaymentDiscountAfterDiscount(int paymentAmountAfterDiscount) {
        String body = DisplayFormatter.displayPaymentAmount(paymentAmountAfterDiscount);
        printTitleAndBody(EventPlannerMessage.PAYMENT_AMOUNT_AFTER_DISCOUNT_MESSAGE, body);
    }

    public void printBadge(BadgeDto badgeDto) {
        String body = badgeDto.badgeName();
        printTitleAndBody(EventPlannerMessage.DECEMBER_EVENT_BADGE_MESSAGE, body);
    }

    public void printBadgeNone() {
        printNone(EventPlannerMessage.DECEMBER_EVENT_BADGE_MESSAGE);
    }

    private void printNone(String title) {
        printTitleAndBody(title, EventPlannerMessage.NONE);
    }

    private void printTitleAndBody(String title, String body) {
        printLineSeparator();
        System.out.println(title);
        System.out.println(body);
    }

    private void printLineSeparator() {
        System.out.println();
    }
}
