package christmas.domain.reservation;

import christmas.dto.OrderMenuDto;
import christmas.exception.IllegalMenuOrderException;

public class Order {

    public static final int MINIMUM_QUANTITY = 1;

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order from(OrderMenuDto menuOrder) {
        String name = menuOrder.name();
        int quantity = menuOrder.quantity();
        Menu menu = Menu.of(name)
                .orElseThrow(IllegalMenuOrderException::new);
        return new Order(menu, quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderMenuDto toDto() {
        return new OrderMenuDto(menu.getName(), quantity);
    }

    public boolean isMenuTypeOf(MenuType menuType) {
        return menu.getMenuType().equals(menuType);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Order order = (Order) object;
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        if (menu != null) {
            return menu.hashCode();
        }
        return 0;
    }
}
