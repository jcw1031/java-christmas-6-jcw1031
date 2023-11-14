package christmas.domain.benefits.discount.policy;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class DayConstant {

    public static final LocalDate START_DATE = LocalDate.of(Year.now().getValue(), Month.DECEMBER, 1);
    public static final LocalDate CHRISTMAS = START_DATE.withDayOfMonth(25);
}
