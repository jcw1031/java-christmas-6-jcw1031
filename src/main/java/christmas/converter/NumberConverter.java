package christmas.converter;

import christmas.exception.ErrorSubject;
import christmas.exception.IllegalReservationException;

public class NumberConverter {

    public static int convert(String value, ErrorSubject errorSubject) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalReservationException(errorSubject);
        }
    }
}
