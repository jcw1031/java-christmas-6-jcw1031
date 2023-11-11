package christmas.domain.reservation;

import christmas.validator.ReservationDateValidator;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class VisitDate {

    public static final int START_RESERVATION_DATE = 1;
    public static final int END_RESERVATION_DATE = 31;

    private final LocalDate date;

    public VisitDate(int day) {
        ReservationDateValidator.validateReservationDateRange(day);
        date = LocalDate.of(Year.now().getValue(), Month.DECEMBER, day);
    }
}
