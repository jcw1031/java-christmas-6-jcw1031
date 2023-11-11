package christmas.validator;

import christmas.domain.reservation.VisitDate;
import christmas.exception.IllegalVisitDateException;

public class VisitDateValidator {

    public static void validateReservationDateRange(int reservationDate) {
        if (isInvalidRangeDate(reservationDate)) {
            throw new IllegalVisitDateException();
        }
    }

    private static boolean isInvalidRangeDate(int reservationDate) {
        return reservationDate < VisitDate.START_RESERVATION_DATE
                || reservationDate > VisitDate.END_RESERVATION_DATE;
    }
}
