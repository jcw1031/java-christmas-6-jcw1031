package christmas.exception;

public class IllegalReservationException extends IllegalArgumentException {

    private final ErrorSubject errorSubject;
    private final String additionalMessage;

    public IllegalReservationException(ErrorSubject errorSubject) {
        this.errorSubject = errorSubject;
        this.additionalMessage = null;
    }

    public IllegalReservationException(ErrorSubject errorSubject, String additionalMessage) {
        this.errorSubject = errorSubject;
        this.additionalMessage = additionalMessage;
    }

    public ErrorSubject getErrorSubject() {
        return errorSubject;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }
}
