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

class OrderTest {

    @DisplayName("존재하지 않는 메뉴를 주문하는 경우 예외가 발생한다.")
    @ValueSource(strings = {"서브웨이-3", "샴페인-2,처음처럼-2", "BHC-1"})
    @ParameterizedTest
    void orderMenusFailWhenNonExistMenu(String orderMenusInput) {
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