package christmas.domain;

import christmas.domain.benefits.EventBenefits;
import christmas.domain.benefits.Giveaway;
import christmas.domain.reservation.Reservation;
import christmas.dto.GiveawayMenuDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PromotionSystem {

    private final Reservation reservation;
    private final EventBenefits eventBenefits;

    public PromotionSystem(Reservation reservation, EventBenefits eventBenefits) {
        this.reservation = reservation;
        this.eventBenefits = eventBenefits;
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

    public Optional<GiveawayMenuDto> determineGiveawayMenu() {
        Optional<Giveaway> giveawayMenu = eventBenefits.generateGiveawayMenu(reservation);
        return giveawayMenu.map(Giveaway::toDto);
    }
}
