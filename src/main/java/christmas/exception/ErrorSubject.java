package christmas.exception;

public enum ErrorSubject {

    DATE("날짜"),
    MENU("메뉴");

    private final String name;

    ErrorSubject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
