package christmas.domain.reservation;

public class Reservation {

    private ReservationDate reservationDate;

    public void createReserveDate(int reservationDay) {
        reservationDate = new ReservationDate(reservationDay);
    }
}
