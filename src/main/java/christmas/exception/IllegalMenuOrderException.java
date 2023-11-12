package christmas.exception;

public class IllegalMenuOrderException extends IllegalReservationException {

    public IllegalMenuOrderException() {
        super(ErrorSubject.MENU);
    }

    public IllegalMenuOrderException(String additionalMessage) {
        super(ErrorSubject.MENU, additionalMessage);
    }
}
