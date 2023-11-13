package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.reservation.Reservation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DiscountPolicies {

    private static final int EVENT_BENEFITS_APPLY_THRESHOLD = 10_000;

    private final List<DiscountPolicy> discountPolies = List.of(
            new DDayDiscountPolicy(),
            new WeekDiscountPolicy(),
            new SpecialDiscountPolicy(),
            new GiveawayDiscountPolicy()
    );

    public List<Discount> calculateDiscounts(Reservation reservation) {
        if (reservation.getTotalOrderAmount() < EVENT_BENEFITS_APPLY_THRESHOLD) {
            return Collections.emptyList();
        }

        return discountPolies.stream()
                .map(discountPolicy -> discountPolicy.calculateDiscountAmount(reservation))
                .flatMap(Optional::stream)
                .toList();
    }
}
