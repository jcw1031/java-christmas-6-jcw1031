package christmas.domain.benefits;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.Discounts;
import christmas.domain.benefits.discount.policy.DiscountPolicies;
import christmas.domain.reservation.Menu;
import christmas.domain.reservation.Reservation;

import java.util.List;
import java.util.Optional;

public class EventBenefits {

    private final DiscountPolicies discountPolicies = new DiscountPolicies();

    private Giveaway giveaway;
    private Discounts discounts;

    public Optional<Giveaway> generateGiveawayMenu(Reservation reservation) {
        int totalOrderAmount = reservation.getTotalOrderAmount();
        if (totalOrderAmount >= Giveaway.GIVEAWAY_EVENT_APPLY_THRESHOLD) {
            giveaway = Giveaway.of(Menu.getGiveaway(), Giveaway.GIVEAWAY_MENUS_NUMBER);
        }
        return Optional.ofNullable(giveaway);
    }

    public Optional<Discounts> generateDiscounts(Reservation reservation) {
        List<Discount> discountsResult = discountPolicies.calculateDiscounts(reservation);
        if (!discountsResult.isEmpty()) {
            discounts = new Discounts(discountsResult);
        }
        return Optional.ofNullable(discounts);
    }
}
