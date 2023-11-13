package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.DiscountType;
import christmas.domain.reservation.Reservation;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Optional;

public class DDayDiscountPolicy implements DiscountPolicy {

    private static final LocalDate START_DATE = LocalDate.of(Year.now().getValue(), Month.DECEMBER, 1);
    private static final LocalDate CHRISTMAS = START_DATE.withDayOfMonth(25);
    private static final int START_DISCOUNT_AMOUNT = 1_000;
    private static final int INCREASE_AMOUNT = 100;

    @Override
    public Optional<Discount> calculateDiscountAmount(Reservation reservation) {
        LocalDate visitDate = reservation.getDate();
        if (visitDate.isAfter(CHRISTMAS)) {
            return Optional.empty();
        }

        Period period = Period.between(START_DATE, visitDate);
        int dDay = period.getDays();
        int discountAmount = START_DISCOUNT_AMOUNT + dDay * INCREASE_AMOUNT;
        Discount discount = Discount.of(DiscountType.CHRISTMAS_D_DAY, discountAmount);
        return Optional.of(discount);
    }
}
