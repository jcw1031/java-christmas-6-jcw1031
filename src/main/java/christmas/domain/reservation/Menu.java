package christmas.domain.reservation;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    ;

    private final String name;

    Menu(String name) {
        this.name = name;
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
