package christmas.domain;

import christmas.converter.OrderMenusConverter;
import christmas.domain.benefits.EventBenefits;
import christmas.domain.reservation.Menu;
import christmas.domain.reservation.Order;
import christmas.domain.reservation.Reservation;
import christmas.dto.DiscountsDto;
import christmas.dto.GiveawayMenuDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @DisplayName("할인 전 총 주문 금액이 12만원 이상인 경우 증정 메뉴를 제공한다.")
    @ValueSource(strings = {"타파스-2,해산물파스타-2,초코케이크-1,레드와인-2", "티본스테이크-3", "초코케이크-8"})
    @ParameterizedTest
    void determineGiveawayMenu(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);

        // when
        promotionSystem.orderMenus(orderMenusDto);
        GiveawayMenuDto giveawayMenu = promotionSystem.determineGiveawayMenu()
                .get();

        // then
        assertThat(giveawayMenu).isNotNull();
        assertThat(giveawayMenu)
                .extracting("name").isEqualTo(Menu.CHAMPAGNE.getName());
        assertThat(giveawayMenu)
                .extracting("quantity").isEqualTo(1);
    }

    @DisplayName("할인 전 총 주문 금액이 12만원 미만인 경우 증정 메뉴를 제공하지 않는다.")
    @ValueSource(strings = {"타파스-1,시저샐러드-2", "티본스테이크-1"})
    @ParameterizedTest
    void determineGiveawayMenuNone(String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);

        // when
        promotionSystem.orderMenus(orderMenusDto);
        Optional<GiveawayMenuDto> giveawayMenu = promotionSystem.determineGiveawayMenu();

        // then
        assertThat(giveawayMenu.isEmpty()).isTrue();
    }

    @DisplayName("할인을 적용한다.")
    @CsvSource(textBlock = """
            1, '타파스-1,시저샐러드-2'
            10, '티본스테이크-1,레드와인-2',
            24, '아이스크림-2'
             """)
    @ParameterizedTest
    void calculateDiscounts(int visitDay, String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);

        // when
        promotionSystem.reserveVisitDate(visitDay);
        promotionSystem.orderMenus(orderMenusDto);
        Optional<DiscountsDto> discounts = promotionSystem.calculateDiscounts();

        // then
        assertThat(discounts).isNotEmpty();
    }

    @DisplayName("주문 금액이 10,000원 미만인 경우 할인이 적용되지 않는다.")
    @CsvSource(textBlock = """
            1, '타파스-1'
            10, '아이스크림-1',
             """)
    @ParameterizedTest
    void calculateDiscountsNone(int visitDay, String orderMenusInput) {
        // given
        List<String> orderMenus = Arrays.stream(orderMenusInput.split(","))
                .toList();
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(orderMenus);

        // when
        promotionSystem.reserveVisitDate(visitDay);
        promotionSystem.orderMenus(orderMenusDto);
        Optional<DiscountsDto> discounts = promotionSystem.calculateDiscounts();

        // then
        assertThat(discounts).isEmpty();
    }
}
