package menu.repository;

import menu.domain.Coach;

import java.util.List;

public interface CoachRepository {
    Coach findByName(String name);
    void save(Coach coach);
    List<Coach> findAll();
}
