package christmas.validator;

import christmas.domain.reservation.MenuType;
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
            throw new IllegalMenuOrderException("**메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다!**");
        }
    }

    public static void validateMenusType(List<Order> orders) {
        orders.stream()
                .filter(MenuOrderValidator::isNotDrink)
                .findAny()
                .orElseThrow(() -> new IllegalMenuOrderException("**음료만 주문할 수 없습니다!**"));
    }

    private static boolean isNotDrink(Order order) {
        return !order.isMenuTypeOf(MenuType.DRINK);
    }
}
