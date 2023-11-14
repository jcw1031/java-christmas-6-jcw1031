package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.DiscountType;
import christmas.domain.reservation.Reservation;
import christmas.domain.reservation.VisitDate;

import java.time.DayOfWeek;
import java.util.Optional;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public Optional<Discount> calculateDiscountAmount(Reservation reservation) {
        VisitDate visitDate = reservation.getVisitDate();
        if (visitDate.isDayOfWeek(DayOfWeek.SUNDAY) || visitDate.isSame(DayConstant.CHRISTMAS)) {
            Discount discount = Discount.of(DiscountType.SPECIAL, DISCOUNT_AMOUNT);
            return Optional.of(discount);
        }
        return Optional.empty();
    }
}
