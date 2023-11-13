package christmas.domain.benefits.discount;

import christmas.dto.DiscountDto;

public class Discount {

    private final DiscountType type;
    private final int amount;

    public Discount(DiscountType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public static Discount of(DiscountType discountType, int discountAmount) {
        return new Discount(discountType, -discountAmount);
    }

    public DiscountDto toDto() {
        return new DiscountDto(type, amount);
    }
}
