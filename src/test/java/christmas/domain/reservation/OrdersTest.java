package christmas.domain.reservation;

import christmas.converter.OrderMenusConverter;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;
import christmas.exception.ErrorSubject;
import christmas.exception.IllegalMenuOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {

    @DisplayName("주문 메뉴가 중복되는 경우 예외가 발생한다.")
    @ValueSource(strings = {"타파스-1,타파스-2", "샴페인-2,샴페인-4"})
    @ParameterizedTest
    void orderMenusFailWhenDuplicateMenu(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        //  when & then
        assertThatThrownBy(() -> Orders.from(orderMenuDtoList))
                .isInstanceOf(IllegalMenuOrderException.class)
                .extracting("errorSubject").isEqualTo(ErrorSubject.ORDER);
    }

    @DisplayName("전체 주문 개수가 20개가 초과하는 경우 예외가 발생한다.")
    @ValueSource(strings = {"타파스-21", "바비큐립-11,아이스크림-10", "타파스-6,해산물파스타-5,초코케이크-7,레드와인-4"})
    @ParameterizedTest
    void orderMenusFailTotalQuantityExceeds(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        //  when & then
        assertThatThrownBy(() -> Orders.from(orderMenuDtoList))
                .isInstanceOf(IllegalMenuOrderException.class)
                .extracting("errorSubject").isEqualTo(ErrorSubject.ORDER);
    }

    @DisplayName("음료만 주문한 경우 예외가 발생한다.")
    @ValueSource(strings = {"제로콜라-2", "제로콜라-1,레드와인-2", "제로콜라-1,레드와인-1,샴페인-1"})
    @ParameterizedTest
    void orderMenusFailOnlyDrink(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        //  when & then
        assertThatThrownBy(() -> Orders.from(orderMenuDtoList))
                .isInstanceOf(IllegalMenuOrderException.class)
                .extracting("errorSubject").isEqualTo(ErrorSubject.ORDER);
    }

    @DisplayName("총 주문 금액을 계산한다.")
    @ValueSource(strings = {"양송이수프-2", "티본스테이크-1,아이스크림-2", "타파스-2,해산물파스타-2,초코케이크-1,레드와인-2"})
    @ParameterizedTest
    void getTotalAmount(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();
        int expectedTotalOrderAmount = orderMenuDtoList
                .stream()
                .mapToInt(orderMenu -> {
                    String name = orderMenu.name();
                    int quantity = orderMenu.quantity();
                    Menu menu = Menu.of(name).get();
                    return menu.getPrice() * quantity;
                })
                .sum();

        //  when
        Orders orders = Orders.from(orderMenuDtoList);
        int totalOrderAmount = orders.getTotalAmount();

        // then
        assertThat(totalOrderAmount).isEqualTo(expectedTotalOrderAmount);
    }
}