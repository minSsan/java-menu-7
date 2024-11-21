package menu.domain;

import java.util.Arrays;

public enum Category {
    일식("일식", 1),
    한식("한식", 2),
    중식("중식", 3),
    아시안("아시안", 4),
    양식("양식", 5),
    ;

    private final String name;
    private final int serial;

    private Category(String name, int serial) {
        this.name = name;
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public static Category of(int serial) {
        return Arrays.stream(values())
                .filter(value -> value.serial == serial)
                .findFirst()
                .orElseThrow();
    }
}
