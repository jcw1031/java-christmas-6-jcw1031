package christmas.domain.reservation;

import christmas.validator.VisitDateValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;

public class VisitDate {

    public static final int START_RESERVATION_DATE = 1;
    public static final int END_RESERVATION_DATE = 31;

    private final LocalDate date;

    public VisitDate(LocalDate date) {
        this.date = date;
    }

    public static VisitDate of(int day) {
        VisitDateValidator.validateReservationDateRange(day);
        LocalDate date = LocalDate.of(Year.now().getValue(), Month.DECEMBER, day);
        return new VisitDate(date);
    }

    public LocalDate date() {
        return date;
    }

    public boolean isAfter(LocalDate date) {
        return this.date.isAfter(date);
    }

    public int betweenDays(LocalDate date) {
        Period period = Period.between(date, this.date);
        return period.getDays();
    }

    public boolean isWeekend() {
        return isDayOfWeek(DayOfWeek.FRIDAY) || isDayOfWeek(DayOfWeek.SATURDAY);
    }

    public boolean isDayOfWeek(DayOfWeek dayOfWeek) {
        return date.getDayOfWeek().equals(dayOfWeek);
    }

    public boolean isSame(LocalDate christmas) {
        return date.isEqual(christmas);
    }
}
