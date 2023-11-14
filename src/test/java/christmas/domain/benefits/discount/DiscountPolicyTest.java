package christmas.domain.benefits.discount;

import christmas.converter.OrderMenusConverter;
import christmas.domain.benefits.discount.policy.DDayDiscountPolicy;
import christmas.domain.benefits.discount.policy.SpecialDiscountPolicy;
import christmas.domain.benefits.discount.policy.WeekDiscountPolicy;
import christmas.domain.reservation.Reservation;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {

    DDayDiscountPolicy dDayDiscountPolicy = new DDayDiscountPolicy();
    WeekDiscountPolicy weekDiscountPolicy = new WeekDiscountPolicy();
    SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
    Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
    }

    @DisplayName("크리스마스 디데이 할인을 계산한다.")
    @CsvSource(textBlock = """
            1, 티본스테이크-1
            25, 티본스테이크-1
            """)
    @ParameterizedTest
    void dDayDiscount(int visitDay, String orderMenusInput) {
        // given
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(List.of(orderMenusInput));
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        reservation.generateVisitDate(visitDay);
        reservation.generateOrders(orderMenuDtoList);

        Optional<Discount> discount = dDayDiscountPolicy.calculateDiscountAmount(reservation);

        // then
        assertThat(discount).isNotEmpty();
        assertThat(discount).get()
                .extracting("type").isEqualTo(DiscountType.CHRISTMAS_D_DAY);
        assertThat(discount).get()
                .extracting("amount").isEqualTo(-(1_000 + (visitDay - 1) * 100));
    }

    @DisplayName("기간을 벗어난 경우 크리스마스 디데이 할인이 적용되지 않는다.")
    @CsvSource(textBlock = """
            26, 티본스테이크-1
            31, 티본스테이크-1
            """)
    @ParameterizedTest
    void dDayDiscountNoneOutOfRange(int visitDay, String orderMenusInput) {
        // given
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(List.of(orderMenusInput));
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        reservation.generateVisitDate(visitDay);
        reservation.generateOrders(orderMenuDtoList);

        Optional<Discount> discount = dDayDiscountPolicy.calculateDiscountAmount(reservation);

        // then
        assertThat(discount).isEmpty();
    }

    @DisplayName("평일/주말 할인을 계산한다.")
    @CsvSource(textBlock = """
            1, 티본스테이크-1
            25, 초코케이크-1
            """)
    @ParameterizedTest
    void weekDiscount(int visitDay, String orderMenusInput) {
        // given
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(List.of(orderMenusInput));
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        reservation.generateVisitDate(visitDay);
        reservation.generateOrders(orderMenuDtoList);

        DiscountType discountType = DiscountType.WEEKDAY;
        DayOfWeek dayOfWeek = LocalDate.of(2023, 12, visitDay).getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            discountType = DiscountType.WEEKEND;
        }


        Optional<Discount> discount = weekDiscountPolicy.calculateDiscountAmount(reservation);

        // then
        assertThat(discount).isNotEmpty();
        assertThat(discount).get()
                .extracting("type").isEqualTo(discountType);
        assertThat(discount).get()
                .extracting("amount").isEqualTo(-2_023);
    }

    @DisplayName("할인 메뉴가 없는 경우 평일/주말 할인이 적용되지 않는다.")
    @CsvSource(textBlock = """
            1, 초코케이크-1
            25, 티본스테이크-1
            """)
    @ParameterizedTest
    void weekDiscountNone(int visitDay, String orderMenusInput) {
        // given
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(List.of(orderMenusInput));
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        reservation.generateVisitDate(visitDay);
        reservation.generateOrders(orderMenuDtoList);

        Optional<Discount> discount = weekDiscountPolicy.calculateDiscountAmount(reservation);

        // then
        assertThat(discount).isEmpty();
    }

    @DisplayName("특별 할인을 계산한다.")
    @CsvSource(textBlock = """
            3, 티본스테이크-1
            10, 티본스테이크-1
            25, 티본스테이크-1
            """)
    @ParameterizedTest
    void specialDiscount(int visitDay, String orderMenusInput) {
        // given
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(List.of(orderMenusInput));
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        reservation.generateVisitDate(visitDay);
        reservation.generateOrders(orderMenuDtoList);

        Optional<Discount> discount = specialDiscountPolicy.calculateDiscountAmount(reservation);

        // then
        assertThat(discount).isNotEmpty();
        assertThat(discount).get()
                .extracting("type").isEqualTo(DiscountType.SPECIAL);
        assertThat(discount).get()
                .extracting("amount").isEqualTo(-1_000);
    }

    @DisplayName("특별 할인 적용 날이 아닌 경우 적용되지 않는다.")
    @CsvSource(textBlock = """
            1, 티본스테이크-1
            11, 티본스테이크-1
            26, 티본스테이크-1
            """)
    @ParameterizedTest
    void specialDiscountNone(int visitDay, String orderMenusInput) {
        // given
        OrderMenusDto orderMenusDto = OrderMenusConverter.convert(List.of(orderMenusInput));
        List<OrderMenuDto> orderMenuDtoList = orderMenusDto.orderMenus();

        // when
        reservation.generateVisitDate(visitDay);
        reservation.generateOrders(orderMenuDtoList);

        Optional<Discount> discount = specialDiscountPolicy.calculateDiscountAmount(reservation);

        // then
        assertThat(discount).isEmpty();
    }
}