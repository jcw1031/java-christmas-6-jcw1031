package christmas.domain.reservation;

import christmas.dto.MenuOrderDto;

import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(List<MenuOrderDto> menuOrders) {
        List<Order> orders = menuOrders.stream()
                .map(Order::from)
                .toList();
        return new Orders(orders);
    }
}
