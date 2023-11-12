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
        List<OrderMenuDto> menuOrders = orderMenusDto.orderMenus();

        //  when & then
        assertThatThrownBy(() -> Orders.from(menuOrders))
                .isInstanceOf(IllegalMenuOrderException.class)
                .extracting("errorSubject").isEqualTo(ErrorSubject.MENU);
    }

    @DisplayName("전체 주문 개수가 20개가 초과하는 경우 예외가 발생한다.")
    @ValueSource(strings = {"타파스-21", "바비큐립-11,아이스크림-10", "타파스-6,해산물파스타-5,초코케이크-7,레드와인-4"})
    @ParameterizedTest
    void orderMenusFailTotalQuantityExceeds(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        List<OrderMenuDto> menuOrders = orderMenusDto.orderMenus();

        //  when & then
        assertThatThrownBy(() -> Orders.from(menuOrders))
                .isInstanceOf(IllegalMenuOrderException.class)
                .extracting("errorSubject").isEqualTo(ErrorSubject.MENU);
    }
}