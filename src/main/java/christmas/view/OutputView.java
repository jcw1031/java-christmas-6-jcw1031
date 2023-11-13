package christmas.view;

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
        printTitleAndBody(EventPlannerMessage.GIVEAWAY_TITLE, EventPlannerMessage.NONE);
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
        printTitleAndBody(EventPlannerMessage.DISCOUNTS_TITLE, EventPlannerMessage.NONE);
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
