package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.DiscountType;
import christmas.domain.reservation.Reservation;
import christmas.domain.reservation.VisitDate;

import java.util.Optional;

public class DDayDiscountPolicy implements DiscountPolicy {

    private static final int START_DISCOUNT_AMOUNT = 1_000;
    private static final int INCREASE_AMOUNT = 100;

    @Override
    public Optional<Discount> calculateDiscountAmount(Reservation reservation) {
        VisitDate visitDate = reservation.getVisitDate();
        if (visitDate.isAfter(DayConstant.CHRISTMAS)) {
            return Optional.empty();
        }

        int dDay = visitDate.betweenDays(DayConstant.START_DATE);
        int discountAmount = START_DISCOUNT_AMOUNT + dDay * INCREASE_AMOUNT;
        Discount discount = Discount.of(DiscountType.CHRISTMAS_D_DAY, discountAmount);
        return Optional.of(discount);
    }
}
