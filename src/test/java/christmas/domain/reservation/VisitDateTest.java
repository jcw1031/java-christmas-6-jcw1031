package christmas.domain.reservation;

import christmas.exception.ErrorSubject;
import christmas.exception.IllegalVisitDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {

    @DisplayName("방문 날짜(일)가 1 ~ 31 범위가 아닌 경우 예외가 발생한다.")
    @ValueSource(ints = {-1, 0, 32, 100})
    @ParameterizedTest
    void reserveDateFailWhenOutOfRange(int reservationDay) {
        // given & when & then
        assertThatThrownBy(() -> VisitDate.of(reservationDay))
                .isInstanceOf(IllegalVisitDateException.class)
                .extracting("errorSubject").isEqualTo(ErrorSubject.DATE);
    }
}