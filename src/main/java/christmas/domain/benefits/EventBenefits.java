package christmas.domain.benefits;

import christmas.domain.reservation.Menu;
import christmas.domain.reservation.Reservation;

import java.util.Optional;

public class EventBenefits {

    private Giveaway giveaway;

    public Optional<Giveaway> generateGiveawayMenu(Reservation reservation) {
        int totalOrderAmount = reservation.getTotalOrderAmount();
        if (totalOrderAmount >= Giveaway.GIVEAWAY_EVENT_APPLY_THRESHOLD) {
            giveaway = Giveaway.of(Menu.CHAMPAGNE, 1);
        }
        return Optional.ofNullable(giveaway);
    }
}
