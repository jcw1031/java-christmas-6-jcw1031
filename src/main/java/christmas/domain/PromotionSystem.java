package christmas.domain;

import christmas.domain.reservation.Reservation;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;

import java.time.LocalDate;
import java.util.List;

public class PromotionSystem {

    private final Reservation reservation;

    public PromotionSystem(Reservation reservation) {
        this.reservation = reservation;
    }

    public void reserveVisitDate(int reservationDay) {
        reservation.generateVisitDate(reservationDay);
    }

    public void orderMenus(OrderMenusDto orderMenusDto) {
        List<OrderMenuDto> orderMenus = orderMenusDto.orderMenus();
        reservation.generateOrders(orderMenus);
    }

    public LocalDate getVisitDate() {
        return reservation.getDate();
    }

    public OrderMenusDto generateOrderMenusHistory() {
        return reservation.generateOrderMenusHistory();
    }

    public int getTotalOrderAmount() {
        return reservation.getTotalOrderAmount();
    }
}
