package christmas.exception;

public enum ErrorSubject {

    DATE("날짜"),
    ORDER("주문");

    private final String name;

    ErrorSubject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
