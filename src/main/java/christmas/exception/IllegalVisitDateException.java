package christmas.exception;

public class IllegalVisitDateException extends IllegalReservationException {

    public IllegalVisitDateException() {
        super(ErrorSubject.DATE);
    }
}
