package christmas.domain;

import christmas.converter.OrderMenusConverter;
import christmas.domain.benefits.EventBenefits;
import christmas.domain.reservation.Menu;
import christmas.domain.reservation.Order;
import christmas.domain.reservation.Reservation;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;
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
    EventBenefits eventBenefits;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        eventBenefits = new EventBenefits();
        promotionSystem = new PromotionSystem(reservation, eventBenefits);
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
    void orderMenus(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        promotionSystem.orderMenus(orderMenusDto);

        List<Order> orders = orderMenuDtoList.stream()
                .map(Order::from)
                .toList();

        // then
        assertThat(reservation)
                .extracting("orders")
                .isNotNull()
                .extracting("orders")
                .isNotNull()
                .asList()
                .isEqualTo(orders);
    }

    @DisplayName("주문 메뉴 내역을 생성한다.")
    @ValueSource(strings = {"양송이수프-2", "티본스테이크-1,아이스크림-2", "타파스-2,해산물파스타-2,초코케이크-1,레드와인-2"})
    @ParameterizedTest
    void generateOrderMenusHistory(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        promotionSystem.orderMenus(orderMenusDto);
        OrderMenusDto orderMenusHistory = promotionSystem.generateOrderMenusHistory();

        // then
        assertThat(orderMenusHistory)
                .extracting("orderMenus")
                .isEqualTo(orderMenuDtoList);
    }

    @DisplayName("할인 전 총 주문 금액을 받아온다.")
    @ValueSource(strings = {"양송이수프-2", "티본스테이크-1,아이스크림-2", "타파스-2,해산물파스타-2,초코케이크-1,레드와인-2"})
    @ParameterizedTest
    void getTotalOrderAmount(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);
        int expectedTotalOrderAmount = orderMenusDto.orderMenus()
                .stream()
                .mapToInt(orderMenu -> {
                    String name = orderMenu.name();
                    int quantity = orderMenu.quantity();
                    Menu menu = Menu.of(name).get();
                    return menu.getPrice() * quantity;
                })
                .sum();

        // when
        promotionSystem.orderMenus(orderMenusDto);
        int totalOrderAmount = promotionSystem.getTotalOrderAmount();

        // then
        assertThat(totalOrderAmount).isEqualTo(expectedTotalOrderAmount);
    }
}
