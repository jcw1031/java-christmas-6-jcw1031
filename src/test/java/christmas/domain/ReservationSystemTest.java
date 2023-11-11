package christmas.domain;

import christmas.domain.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationSystemTest {

    ReservationSystem reservationSystem;
    Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        reservationSystem = new ReservationSystem(reservation);
    }

    @DisplayName("방문 날짜(일)를 지정한다.")
    @ValueSource(ints = {1, 7, 10, 16, 25, 31})
    @ParameterizedTest
    void reserveDate(int visitDay) {
        // given & when
        reservationSystem.reserveVisitDate(visitDay);

        // then
        assertThat(reservation)
                .extracting("visitDate")
                .extracting("date")
                .isEqualTo(LocalDate.of(2023, 12, visitDay));
    }
}
