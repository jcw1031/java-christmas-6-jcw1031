package christmas.exception;

public class IllegalMenuOrderException extends IllegalReservationException {

    public IllegalMenuOrderException() {
        super(ErrorSubject.ORDER);
    }

    public IllegalMenuOrderException(String additionalMessage) {
        super(ErrorSubject.ORDER, additionalMessage);
    }
}
