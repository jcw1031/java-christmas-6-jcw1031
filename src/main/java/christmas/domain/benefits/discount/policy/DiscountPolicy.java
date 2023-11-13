package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.reservation.Reservation;

import java.util.Optional;

public interface DiscountPolicy {

    Optional<Discount> calculateDiscountAmount(Reservation reservation);
}
