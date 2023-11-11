package christmas.domain;

import christmas.domain.reservation.Reservation;

public class ReservationSystem {

    private final Reservation reservation;

    public ReservationSystem(Reservation reservation) {
        this.reservation = reservation;
    }

    public void reserveDate(int reservationDay) {
        reservation.createReserveDate(reservationDay);
    }
}
