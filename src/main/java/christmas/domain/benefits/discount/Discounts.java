package christmas.domain.benefits.discount;

import christmas.dto.DiscountDto;
import christmas.dto.DiscountsDto;

import java.util.List;

public class Discounts {

    private final List<Discount> discounts;

    public Discounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public DiscountsDto toDto() {
        List<DiscountDto> discountDtoList = discounts.stream()
                .map(Discount::toDto)
                .toList();
        return new DiscountsDto(discountDtoList);
    }
}
