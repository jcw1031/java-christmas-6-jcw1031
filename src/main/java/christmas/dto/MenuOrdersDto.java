package christmas.dto;

import java.util.Collections;
import java.util.List;

public record MenuOrdersDto(List<MenuOrderDto> menuOrders) {

    @Override
    public List<MenuOrderDto> menuOrders() {
        return Collections.unmodifiableList(menuOrders);
    }
}
