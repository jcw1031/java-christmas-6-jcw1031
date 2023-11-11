package christmas.domain.reservation;

import christmas.dto.MenuOrderDto;
import christmas.exception.IllegalMenuOrderException;

public class Order {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order from(MenuOrderDto menuOrder) {
        String name = menuOrder.name();
        int quantity = menuOrder.quantity();
        Menu menu = Menu.of(name)
                .orElseThrow(IllegalMenuOrderException::new);
        return new Order(menu, quantity);
    }
}
