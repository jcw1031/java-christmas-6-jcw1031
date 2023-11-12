package christmas.domain.reservation;

import java.util.Arrays;
import java.util.Optional;

import static christmas.domain.reservation.MenuType.APPETIZER;
import static christmas.domain.reservation.MenuType.DESSERT;
import static christmas.domain.reservation.MenuType.DRINK;
import static christmas.domain.reservation.MenuType.MAIN;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", APPETIZER, 6_000),
    TAPAS("타파스", APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", APPETIZER, 8_000),

    T_BONE_STEAK("티본스테이크", MAIN, 55_000),
    BARBECUE_RIBS("바비큐립", MAIN, 54_000),
    SEAFOOD_PASTA("해산물파스타", MAIN, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", MAIN, 25_000),

    CHOCOLATE_CAKE("초코케이크", DESSERT, 15_000),
    ICE_CREAM("아이스크림", DESSERT, 5_000),

    ZERO_COLA("제로콜라", DRINK, 3_000),
    RED_WINE("레드와인", DRINK, 60_000),
    CHAMPAGNE("샴페인", DRINK, 25_000);

    private final String name;
    private final MenuType menuType;
    private final int price;

    Menu(String name, MenuType menuType, int price) {
        this.name = name;
        this.menuType = menuType;
        this.price = price;
    }

    public static Optional<Menu> of(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isNameMatch(name))
                .findAny();
    }

    public String getName() {
        return name;
    }

    private boolean isNameMatch(String name) {
        return this.name.equals(name);
    }
}
