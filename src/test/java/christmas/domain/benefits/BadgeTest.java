package christmas.domain.benefits;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BadgeTest {

    @DisplayName("혜택 금액별로 배지가 생성된다.")
    @CsvSource(textBlock = """
            5000, STAR
            10000, TREE
            20000, SANTA
             """)
    @ParameterizedTest
    void generateBadge(int benefitsAmount, String badgeName) {
        // given & when
        Badge badge = Badge.of(benefitsAmount).get();

        // then
        assertThat(badge.name()).isEqualTo(badgeName);
    }

    @DisplayName("혜택 금액이 5,000원이 넘지 않으면 배지가 생성되지 않는다.")
    @Test
    void generateBadgeNone() {
        // given & when
        Optional<Badge> badge = Badge.of(4_999);

        // then
        assertThat(badge).isEmpty();
    }
}
