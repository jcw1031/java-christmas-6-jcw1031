package christmas.view;

import christmas.dto.GiveawayMenuDto;
import christmas.dto.OrderMenuDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisplayFormatter {

    private static final String MENU_DISPLAY_FORMAT = "%s %d개";
    private static final String PRICE_DISPLAY_FORMAT = "%,d원";

    public static String displayOrderMenu(OrderMenuDto orderMenu) {
        return String.format(MENU_DISPLAY_FORMAT, orderMenu.name(), orderMenu.quantity());
    }

    public static String displayGiveawayMenu(GiveawayMenuDto giveawayMenu) {
        return String.format(MENU_DISPLAY_FORMAT, giveawayMenu.name(), giveawayMenu.quantity());
    }

    public static String displayOrderAmount(int totalOrderAmount) {
        return String.format(PRICE_DISPLAY_FORMAT, totalOrderAmount);
    }

    public static String displayVisitDate(LocalDate visitDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM월 dd일");
        return visitDate.format(dateTimeFormatter);
    }
}
