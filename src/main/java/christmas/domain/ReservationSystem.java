package christmas.domain;

import christmas.domain.reservation.Reservation;

public class ReservationSystem {

    private final Reservation reservation;

    public ReservationSystem(Reservation reservation) {
        this.reservation = reservation;
    }

    public void reserve(int reservationDay) {
        reservation.reserveDate(reservationDay);
    }
}
