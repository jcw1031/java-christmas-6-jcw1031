package christmas.domain.reservation;

import christmas.exception.InputErrorMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationDateTest {

    @DisplayName("방문 날짜(일)가 1 ~ 31 범위가 아닌 경우 예외가 발생한다.")
    @ValueSource(ints = {-1, 0, 32, 100})
    @ParameterizedTest
    void reserveDateFailWhenOutOfRange(int reservationDay) {
        // given & when & then
        assertThatThrownBy(() -> new ReservationDate(reservationDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InputErrorMessages.INVALID_RESERVATION_DATE_MESSAGE);
    }
}