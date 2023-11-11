package christmas.converter;

import christmas.dto.MenuOrderDto;
import christmas.dto.MenuOrdersDto;
import christmas.exception.ErrorSubject;
import christmas.validator.MenuOrderValidator;

import java.util.List;

public class MenuOrdersConverter {

    private static final String ORDER_MENU_DELIMITER = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;

    public static MenuOrdersDto convert(List<String> values) {
        List<MenuOrderDto> menuOrder = splitMenusNameAndQuantity(values).stream()
                .map(MenuOrdersConverter::convertToDto)
                .toList();
        return new MenuOrdersDto(menuOrder);
    }

    private static List<String[]> splitMenusNameAndQuantity(List<String> values) {
        List<String[]> result = values.stream()
                .map(nameAndQuantity -> nameAndQuantity.split(ORDER_MENU_DELIMITER))
                .toList();
        MenuOrderValidator.validateOrderMenusInputFormat(result);
        return result;
    }

    private static MenuOrderDto convertToDto(String[] nameAndQuantity) {
        String name = nameAndQuantity[MENU_NAME_INDEX];
        int quantity = NumberConverter.convert(nameAndQuantity[MENU_QUANTITY_INDEX], ErrorSubject.MENU);
        MenuOrderValidator.validateOrderMenuQuantity(quantity);
        return new MenuOrderDto(name, quantity);
    }
}
