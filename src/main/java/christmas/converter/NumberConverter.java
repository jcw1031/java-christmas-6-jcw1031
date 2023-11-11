package christmas.converter;

import christmas.exception.ErrorSubject;
import christmas.exception.IllegalVisitDateException;

public class NumberConverter {

    public static int convert(String value, ErrorSubject errorSubject) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalVisitDateException();
        }
    }
}
