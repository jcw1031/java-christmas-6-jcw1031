package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.InputErrorMessages;

public class InputView {

    public int inputReservationDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine()
                .trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(InputErrorMessages.INVALID_RESERVATION_DATE_MESSAGE);
        }
    }

}
