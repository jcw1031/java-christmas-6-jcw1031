package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.reservation.Reservation;

import java.util.Optional;

public class GiveawayDiscountPolicy implements DiscountPolicy {

    @Override
    public Optional<Discount> calculateDiscountAmount(Reservation reservation) {
        return Optional.empty();
    }
}
