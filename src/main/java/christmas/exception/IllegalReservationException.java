package christmas.exception;

public abstract class IllegalReservationException extends IllegalArgumentException {

    private final ErrorSubject errorSubject;

    public IllegalReservationException(ErrorSubject errorSubject) {
        this.errorSubject = errorSubject;
    }

    public ErrorSubject getErrorSubject() {
        return errorSubject;
    }
}
