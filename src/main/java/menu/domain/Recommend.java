package menu.domain;

import java.util.EnumMap;

public record Recommend(EnumMap<DayOfWeek, Menu> recommends) {
    public static Recommend getInit() {
        EnumMap<DayOfWeek, Menu> init = new EnumMap<>(DayOfWeek.class);
        return new Recommend(init);
    }

    public void setRecommendMenu(DayOfWeek day, Menu menu) {
        recommends.put(day, menu);
    }

    public int getMenuCount(Menu menu) {
        return recommends.values().stream()
                .filter(m -> m.equals(menu))
                .toList()
                .size();
    }
}
