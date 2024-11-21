package menu.repository;

import menu.domain.Category;
import menu.domain.DayOfWeek;

import java.util.List;

public interface DayCategoryRepository {
    void save(DayOfWeek day, Category category);
    Category findCategoryByDay(DayOfWeek day);
    List<Category> findAllCategory();
}
