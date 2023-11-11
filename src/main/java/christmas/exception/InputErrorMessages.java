package christmas.exception;

public class InputErrorMessages {

    private static final String MESSAGE_FORMAT = "[ERROR] 유효하지 않은 %s입니다. 다시 입력해 주세요.";
    public static final String INVALID_RESERVATION_DATE_MESSAGE = String.format(MESSAGE_FORMAT, "날짜");
}
