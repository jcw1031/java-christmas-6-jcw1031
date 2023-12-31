package christmas.domain.reservation;

import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;
import christmas.validator.MenuOrderValidator;

import java.util.List;

public class Orders {

    public static final int MAXIMUM_TOTAL_QUANTITY = 20;

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(List<OrderMenuDto> menuOrders) {
        List<Order> orders = menuOrders.stream()
                .map(Order::from)
                .toList();
        MenuOrderValidator.validateDuplicateMenu(orders);
        MenuOrderValidator.validateTotalQuantity(orders);
        MenuOrderValidator.validateMenusType(orders);
        return new Orders(orders);
    }

    public OrderMenusDto getOrderMenus() {
        List<OrderMenuDto> orderMenus = orders.stream()
                .map(Order::toDto)
                .toList();
        return new OrderMenusDto(orderMenus);
    }

    public int getTotalAmount() {
        return orders.stream()
                .mapToInt(Order::getAmount)
                .sum();
    }

    public int getMenusCountTypeOf(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.isMenuTypeOf(menuType))
                .mapToInt(Order::getQuantity)
                .sum();
    }
}
