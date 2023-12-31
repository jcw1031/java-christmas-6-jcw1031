package christmas.exception;

public class InputErrorMessages {

    private static final String MESSAGE_FORMAT = "[ERROR] 유효하지 않은 %s입니다. 다시 입력해 주세요.";

    public static String of(ErrorSubject errorSubject, String additionalMessage) {
        String message = String.format(MESSAGE_FORMAT, errorSubject.getName());
        if (additionalMessage != null) {
            return String.join(" ", message, additionalMessage);
        }
        return message;
    }
}
