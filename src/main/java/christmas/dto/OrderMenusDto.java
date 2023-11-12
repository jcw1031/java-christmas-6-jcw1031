package christmas.dto;

import christmas.domain.reservation.Order;

import java.util.List;

public record OrderMenusDto(List<OrderMenuDto> orderMenus) {

    public static OrderMenusDto from(List<Order> orders) {
        List<OrderMenuDto> orderMenus = orders.stream()
                .map(Order::toDto)
                .toList();
        return new OrderMenusDto(orderMenus);
    }
}
