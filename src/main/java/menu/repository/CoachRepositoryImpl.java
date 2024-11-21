package menu.repository;

import menu.domain.Coach;

import java.util.*;

public class CoachRepositoryImpl implements CoachRepository {
    private final List<Coach> items = new ArrayList<>();

    @Override
    public Coach findByName(String name) {
        return items.stream()
                .filter(coach -> coach.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Coach coach) {
        items.add(coach);
    }

    @Override
    public List<Coach> findAll() {
        return Collections.unmodifiableList(items);
    }
}
