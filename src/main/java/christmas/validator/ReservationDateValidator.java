package christmas.validator;

import christmas.domain.reservation.ReservationDate;
import christmas.exception.InputErrorMessages;

public class ReservationDateValidator {

    public static void validateReservationDateRange(int reservationDate) {
        if (isInvalidRangeDate(reservationDate)) {
            throw new IllegalArgumentException(InputErrorMessages.INVALID_RESERVATION_DATE_MESSAGE);
        }
    }

    private static boolean isInvalidRangeDate(int reservationDate) {
        return reservationDate < ReservationDate.START_RESERVATION_DATE
                || reservationDate > ReservationDate.END_RESERVATION_DATE;
    }
}
