package christmas.validator;

import christmas.domain.reservation.VisitDate;
import christmas.exception.InputErrorMessages;

public class ReservationDateValidator {

    public static void validateReservationDateRange(int reservationDate) {
        if (isInvalidRangeDate(reservationDate)) {
            throw new IllegalArgumentException(InputErrorMessages.INVALID_RESERVATION_DATE_MESSAGE);
        }
    }

    private static boolean isInvalidRangeDate(int reservationDate) {
        return reservationDate < VisitDate.START_RESERVATION_DATE
                || reservationDate > VisitDate.END_RESERVATION_DATE;
    }
}
