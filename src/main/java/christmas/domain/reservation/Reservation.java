package christmas.domain.reservation;

import christmas.dto.MenuOrderDto;

import java.util.List;

public class Reservation {

    private VisitDate visitDate;
    private Orders orders;

    public void createVisitDate(int visitDay) {
        visitDate = VisitDate.of(visitDay);
    }

    public void createOrders(List<MenuOrderDto> menuOrders) {
        orders = Orders.from(menuOrders);
    }
}
