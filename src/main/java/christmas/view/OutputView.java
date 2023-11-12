package christmas.view;

import christmas.dto.OrderMenusDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OutputView {

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventBenefitsMessage(LocalDate visitDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM월 dd일");
        System.out.printf("%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", visitDate.format(dateTimeFormatter));
        printLineSeparator();
    }

    private void printLineSeparator() {
        System.out.println();
    }

    public void printOrderMenus(OrderMenusDto orderMenus) {
        System.out.println("<주문 메뉴>");
        orderMenus.orderMenus()
                .stream()
                .map(DisplayFormatter::displayOrderMenu)
                .forEach(System.out::println);
    }
}
