package christmas.domain.reservation;

import java.util.Arrays;
import java.util.Optional;

import static christmas.domain.reservation.MenuType.APPETIZER;
import static christmas.domain.reservation.MenuType.DESSERT;
import static christmas.domain.reservation.MenuType.DRINK;
import static christmas.domain.reservation.MenuType.MAIN;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", APPETIZER),
    TAPAS("타파스", APPETIZER),
    CAESAR_SALAD("시저샐러드", APPETIZER),

    T_BONE_STEAK("티본스테이크", MAIN),
    BARBECUE_RIBS("바비큐립", MAIN),
    SEAFOOD_PASTA("해산물파스타", MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", MAIN),

    CHOCOLATE_CAKE("초콜릿케이크", DESSERT),
    ICE_CREAM("아이스크림", DESSERT),

    ZERO_COLA("제로콜라", DRINK),
    RED_WINE("레드와인", DRINK),
    CHAMPAGNE("샴페인", DRINK);

    private final String name;
    private final MenuType menuType;

    Menu(String name, MenuType menuType) {
        this.name = name;
        this.menuType = menuType;
    }

    public static Optional<Menu> of(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.isNameMatch(name))
                .findAny();
    }

    private boolean isNameMatch(String name) {
        return this.name.equals(name);
    }
}
