package christmas.domain.benefits;

import christmas.domain.reservation.Menu;
import christmas.dto.GiveawayMenuDto;

public class Giveaway {

    public static final int GIVEAWAY_EVENT_APPLY_THRESHOLD = 120_000;
    public static final int GIVEAWAY_MENUS_NUMBER = 1;

    private final Menu menu;
    private final int qunatity;

    public Giveaway(Menu menu, int qunatity) {
        this.menu = menu;
        this.qunatity = qunatity;
    }

    public static Giveaway of(Menu menu, int quantity) {
        return new Giveaway(menu, quantity);
    }

    public GiveawayMenuDto toDto() {
        return new GiveawayMenuDto(menu.getName(), qunatity);
    }
}
