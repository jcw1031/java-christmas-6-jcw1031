package christmas.converter;

public class NumberConverter {

    public static int convert(String value, String errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
