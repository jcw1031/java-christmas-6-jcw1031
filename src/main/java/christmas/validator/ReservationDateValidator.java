package christmas.validator;

import christmas.domain.reservation.VisitDate;
import christmas.exception.ErrorSubject;
import christmas.exception.IllegalInputException;

public class ReservationDateValidator {

    public static void validateReservationDateRange(int reservationDate) {
        if (isInvalidRangeDate(reservationDate)) {
            throw new IllegalInputException(ErrorSubject.DATE);
        }
    }

    private static boolean isInvalidRangeDate(int reservationDate) {
        return reservationDate < VisitDate.START_RESERVATION_DATE
                || reservationDate > VisitDate.END_RESERVATION_DATE;
    }
}
