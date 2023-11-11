package christmas.domain.reservation;

public class Reservation {

    private ReservationDate reservationDate;

    public void reserveDate(int reservationDay) {
        reservationDate = new ReservationDate(reservationDay);
    }
}
