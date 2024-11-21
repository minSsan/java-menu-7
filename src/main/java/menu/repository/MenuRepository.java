package menu.repository;

import menu.domain.Category;
import menu.domain.Menu;

import java.util.List;

public interface MenuRepository {
    void save(Menu menu);
    List<Menu> findAll();
    List<Menu> findByCategory(Category category);
    Menu findByName(String name);
}
