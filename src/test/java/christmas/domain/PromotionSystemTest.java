package christmas.domain;

import christmas.converter.MenuOrdersConverter;
import christmas.domain.reservation.Order;
import christmas.domain.reservation.Reservation;
import christmas.dto.MenuOrderDto;
import christmas.dto.MenuOrdersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionSystemTest {

    PromotionSystem promotionSystem;
    Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        promotionSystem = new PromotionSystem(reservation);
    }

    @DisplayName("방문 날짜(일)를 지정한다.")
    @ValueSource(ints = {1, 7, 10, 16, 25, 31})
    @ParameterizedTest
    void reserveVisitDate(int visitDay) {
        // given & when
        promotionSystem.reserveVisitDate(visitDay);

        // then
        assertThat(reservation)
                .extracting("visitDate")
                .extracting("date")
                .isEqualTo(LocalDate.of(2023, 12, visitDay));
    }

    @DisplayName("메뉴를 주문한다.")
    @ValueSource(strings = {"양송이수프-2", "티본스테이크-1,아이스크림-2", "타파스-2,해산물파스타-2,초코케이크-1,레드와인-2"})
    @ParameterizedTest
    void reserveVisitDate(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        MenuOrdersDto menuOrdersDto = MenuOrdersConverter.convert(orderMenus);
        List<MenuOrderDto> menuOrders = menuOrdersDto.menuOrders();

        // when
        promotionSystem.orderMenus(new MenuOrdersDto(menuOrders));

        List<Order> orders = menuOrders.stream()
                .map(Order::from)
                .toList();

        // then
        assertThat(reservation)
                .extracting("orders")
                .isNotNull()
                .extracting("orders")
                .isNotNull()
                .asList()
                .contains(orders.toArray());
    }
}
