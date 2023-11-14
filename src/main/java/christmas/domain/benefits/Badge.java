package christmas.domain.benefits;

import christmas.dto.BadgeDto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int applyAmount;

    Badge(String name, int applyAmount) {
        this.name = name;
        this.applyAmount = applyAmount;
    }

    public int getApplyAmount() {
        return applyAmount;
    }

    public static Optional<Badge> of(int totalBenefitsAmount) {
        return Arrays.stream(Badge.values())
                .sorted(Comparator.comparing(Badge::getApplyAmount).reversed())
                .filter(badge -> badge.applyAmount <= Math.abs(totalBenefitsAmount))
                .findFirst();
    }

    public BadgeDto toDto() {
        return new BadgeDto(name);
    }
}
