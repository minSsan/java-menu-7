package menu.repository;

import menu.domain.Category;
import menu.domain.DayOfWeek;

import java.util.EnumMap;
import java.util.List;

public class DayCategoryRepositoryImpl implements DayCategoryRepository {
    private final EnumMap<DayOfWeek, Category> items;

    public DayCategoryRepositoryImpl() {
        this.items = new EnumMap<>(DayOfWeek.class);
    }

    @Override
    public void save(DayOfWeek day, Category category) {
        items.put(day, category);
    }

    @Override
    public Category findCategoryByDay(DayOfWeek day) {
        return items.get(day);
    }

    @Override
    public List<Category> findAllCategory() {
        return items.values().stream().toList();
    }
}
