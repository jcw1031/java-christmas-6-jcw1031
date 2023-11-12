package christmas.converter;

import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;
import christmas.exception.ErrorSubject;
import christmas.validator.MenuOrderValidator;

import java.util.List;

public class OrderMenusConverter {

    private static final String ORDER_MENU_DELIMITER = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;

    public static OrderMenusDto convert(List<String> values) {
        List<OrderMenuDto> menuOrder = splitMenusNameAndQuantity(values).stream()
                .map(OrderMenusConverter::convertToDto)
                .toList();
        return new OrderMenusDto(menuOrder);
    }

    private static List<String[]> splitMenusNameAndQuantity(List<String> values) {
        List<String[]> result = values.stream()
                .map(nameAndQuantity -> nameAndQuantity.split(ORDER_MENU_DELIMITER))
                .toList();
        MenuOrderValidator.validateOrderMenusInputFormat(result);
        return result;
    }

    private static OrderMenuDto convertToDto(String[] nameAndQuantity) {
        String name = nameAndQuantity[MENU_NAME_INDEX];
        int quantity = NumberConverter.convert(nameAndQuantity[MENU_QUANTITY_INDEX], ErrorSubject.ORDER);
        MenuOrderValidator.validateOrderMenuQuantity(quantity);
        return new OrderMenuDto(name, quantity);
    }
}
