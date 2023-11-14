package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.Giveaway;
import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.DiscountType;
import christmas.domain.reservation.Menu;
import christmas.domain.reservation.Reservation;

import java.util.Optional;

public class GiveawayDiscountPolicy implements DiscountPolicy {

    @Override
    public Optional<Discount> calculateDiscountAmount(Reservation reservation) {
        int totalOrderAmount = reservation.getTotalOrderAmount();
        if (totalOrderAmount >= Giveaway.GIVEAWAY_EVENT_APPLY_THRESHOLD) {
            Menu giveawayMenu = Menu.getGiveaway();
            Discount discount = Discount.of(DiscountType.GIVEAWAY, giveawayMenu.getPrice());
            return Optional.of(discount);
        }
        return Optional.empty();
    }
}
