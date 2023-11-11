package christmas.domain;

import christmas.domain.reservation.Reservation;
import christmas.dto.MenuOrderDto;
import christmas.dto.MenuOrdersDto;

import java.util.List;

public class PromotionSystem {

    private final Reservation reservation;

    public PromotionSystem(Reservation reservation) {
        this.reservation = reservation;
    }

    public void reserveVisitDate(int reservationDay) {
        reservation.createVisitDate(reservationDay);
    }

    public void orderMenus(MenuOrdersDto menuOrdersDto) {
        List<MenuOrderDto> menuOrders = menuOrdersDto.menuOrders();
        reservation.createOrders(menuOrders);
    }
}
