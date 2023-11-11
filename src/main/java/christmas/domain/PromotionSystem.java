package christmas.domain;

import christmas.domain.reservation.Reservation;

public class PromotionSystem {

    private final Reservation reservation;

    public PromotionSystem(Reservation reservation) {
        this.reservation = reservation;
    }

    public void reserveVisitDate(int reservationDay) {
        reservation.createVisitDate(reservationDay);
    }
}
