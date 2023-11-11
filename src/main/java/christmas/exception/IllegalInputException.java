package christmas.exception;

public class IllegalInputException extends IllegalArgumentException {

    private final ErrorSubject errorSubject;

    public IllegalInputException(ErrorSubject errorSubject) {
        this.errorSubject = errorSubject;
    }

    public ErrorSubject getSubject() {
        return errorSubject;
    }
}
