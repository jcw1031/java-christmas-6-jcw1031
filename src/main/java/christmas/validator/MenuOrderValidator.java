package christmas.validator;

import christmas.domain.reservation.Order;
import christmas.domain.reservation.Orders;
import christmas.exception.IllegalMenuOrderException;

import java.util.List;

public class MenuOrderValidator {

    public static void validateOrderMenusInputFormat(List<String[]> orderMenusInput) {
        boolean anyMatch = orderMenusInput.stream()
                .anyMatch(nameAndQuantity -> nameAndQuantity.length != 2);
        if (anyMatch) {
            throw new IllegalMenuOrderException();
        }
    }

    public static void validateOrderMenuQuantity(int quantity) {
        if (quantity < Order.MINIMUM_QUANTITY) {
            throw new IllegalMenuOrderException();
        }
    }

    public static void validateDuplicateMenu(List<Order> orders) {
        int uniqueMenusCount = (int) orders.stream()
                .distinct()
                .count();
        if (orders.size() != uniqueMenusCount) {
            throw new IllegalMenuOrderException();
        }
    }

    public static void validateTotalQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
        if (totalQuantity > Orders.MAXIMUM_TOTAL_QUANTITY) {
            throw new IllegalMenuOrderException();
        }
    }
}
