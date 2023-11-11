package christmas.exception;

public class IllegalMenuOrderException extends IllegalReservationException {

    public IllegalMenuOrderException() {
        super(ErrorSubject.MENU);
    }
}
