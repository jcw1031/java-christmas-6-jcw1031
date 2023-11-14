package christmas.domain.benefits;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.DiscountType;
import christmas.domain.benefits.discount.Discounts;
import christmas.domain.benefits.discount.policy.DiscountPolicies;
import christmas.domain.reservation.Menu;
import christmas.domain.reservation.Reservation;

import java.util.List;
import java.util.Optional;

public class EventBenefits {

    public static final int EVENT_BENEFITS_APPLY_THRESHOLD = 10_000;

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

    public int getTotalAmount() {
        List<Discount> discounts = this.discounts.getDiscounts();
        return discounts.stream()
                .mapToInt(Discount::getAmount)
                .sum();
    }

    public int getDiscountAmount() {
        List<Discount> discounts = this.discounts.getDiscounts();
        return discounts.stream()
                .filter(discount -> !discount.isTypeOf(DiscountType.GIVEAWAY))
                .mapToInt(Discount::getAmount)
                .sum();
    }

    public Optional<Badge> generateBadge(Reservation reservation) {
        int totalBenefitsAmount = getTotalAmount();
        return Badge.of(totalBenefitsAmount);
    }
}
