package menu.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum DayOfWeek {
    월(1),
    화(2),
    수(3),
    목(4),
    금(5),
    ;

    private final int priority;

    private DayOfWeek(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public static List<DayOfWeek> getSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingInt(DayOfWeek::getPriority))
                .toList();
    }
}
