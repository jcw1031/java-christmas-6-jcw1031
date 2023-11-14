package christmas.domain.benefits.discount.policy;

import christmas.domain.benefits.discount.Discount;
import christmas.domain.benefits.discount.DiscountType;
import christmas.domain.reservation.MenuType;
import christmas.domain.reservation.Orders;
import christmas.domain.reservation.Reservation;
import christmas.domain.reservation.VisitDate;

import java.util.Optional;

public class WeekDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_AMOUNT = 2_023;

    @Override
    public Optional<Discount> calculateDiscountAmount(Reservation reservation) {
        VisitDate visitDate = reservation.getVisitDate();
        Orders orders = reservation.getOrders();
        if (visitDate.isWeekend()) {
            return getWeekendDiscount(orders);
        }
        return getWeekdayDiscount(orders);
    }

    private Optional<Discount> getWeekdayDiscount(Orders orders) {
        int menusCount = orders.getMenusCountTypeOf(MenuType.DESSERT);
        if (menusCount == 0) {
            return Optional.empty();
        }

        Discount discount = Discount.of(DiscountType.WEEKDAY, menusCount * DISCOUNT_AMOUNT);
        return Optional.of(discount);
    }

    private Optional<Discount> getWeekendDiscount(Orders orders) {
        int menusCount = orders.getMenusCountTypeOf(MenuType.MAIN);
        if (menusCount == 0) {
            return Optional.empty();
        }

        Discount discount = Discount.of(DiscountType.WEEKEND, menusCount * DISCOUNT_AMOUNT);
        return Optional.of(discount);
    }
}
