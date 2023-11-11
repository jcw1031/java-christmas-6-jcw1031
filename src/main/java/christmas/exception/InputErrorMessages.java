package christmas.exception;

import java.util.EnumMap;
import java.util.Map;

public class InputErrorMessages {

    private static final String MESSAGE_FORMAT = "[ERROR] 유효하지 않은 %s입니다. 다시 입력해 주세요.";
    private static final Map<ErrorSubject, String> MESSAGES = new EnumMap<>(ErrorSubject.class);

    static {
        MESSAGES.put(ErrorSubject.DATE, String.format(MESSAGE_FORMAT, "날짜"));
    }

    public static String of(ErrorSubject errorSubject) {
        return MESSAGES.get(errorSubject);
    }
}
