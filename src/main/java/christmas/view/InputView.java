package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.converter.MenuOrdersConverter;
import christmas.converter.NumberConverter;
import christmas.dto.MenuOrdersDto;
import christmas.exception.ErrorSubject;

import java.util.Arrays;
import java.util.List;

public class InputView {

    public int inputReservationDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = readInput();
        return NumberConverter.convert(input, ErrorSubject.DATE);
    }

    public MenuOrdersDto inputOrderMenus() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        List<String> input = readInput(",");
        return MenuOrdersConverter.convert(input);
    }

    private String readInput() {
        return Console.readLine()
                .trim();
    }

    private List<String> readInput(String delimiter) {
        return Arrays.stream(readInput().split(delimiter))
                .map(String::trim)
                .toList();
    }
}
