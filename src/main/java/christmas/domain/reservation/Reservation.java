package christmas.domain.reservation;

import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;

import java.util.List;

public class Reservation {

    private VisitDate visitDate;
    private Orders orders;

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public Orders getOrders() {
        return orders;
    }

    public void generateVisitDate(int visitDay) {
        visitDate = VisitDate.of(visitDay);
    }

    public void generateOrders(List<OrderMenuDto> menuOrders) {
        orders = Orders.from(menuOrders);
    }

    public OrderMenusDto generateOrderMenusHistory() {
        return orders.getOrderMenus();
    }

    public int getTotalOrderAmount() {
        return orders.getTotalAmount();
    }
}
