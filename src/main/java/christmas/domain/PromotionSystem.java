package christmas.domain;

import christmas.domain.reservation.Reservation;
import christmas.domain.reservation.VisitDate;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;

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
        List<OrderMenuDto> menuOrders = orderMenusDto.orderMenus();
        reservation.generateOrders(menuOrders);
    }

    public VisitDate getVisitDate() {
        return reservation.getVisitDate();
    }

    public OrderMenusDto generateOrderMenusHistory() {
        return reservation.generateOrderMenusHistory();
    }

    public int getTotalOrderAmount() {
        return reservation.getTotalOrderAmount();
    }
}
