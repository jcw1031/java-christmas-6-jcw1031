package christmas.domain.benefits.discount;

public enum DiscountType {

    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    GIVEAWAY("증정 이벤트");

    private final String discountName;

    DiscountType(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountName() {
        return discountName;
    }
}
