package christmas.dto;

import christmas.domain.benefits.discount.DiscountType;

public record DiscountDto(DiscountType discountType, int discountAmount) {
}
