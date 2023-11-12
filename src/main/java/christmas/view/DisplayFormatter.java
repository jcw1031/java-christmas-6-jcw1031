package christmas.view;

import christmas.dto.OrderMenuDto;

public class DisplayFormatter {

    private static final String MENU_DISPLAY_FORMAT = "%s %d개";

    public static String displayOrderMenu(OrderMenuDto orderMenu) {
        return String.format(MENU_DISPLAY_FORMAT, orderMenu.name(), orderMenu.quantity());
    }
}
