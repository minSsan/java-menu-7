package menu.repository;

import menu.domain.Coach;
import menu.domain.Recommend;
import menu.infrastructure.CustomException;

import java.util.HashMap;
import java.util.Map;

public class RecommendRepositoryImpl implements RecommendRepository {
    private final Map<Coach, Recommend> items;

    public RecommendRepositoryImpl() {
        this.items = new HashMap<>();
    }

    @Override
    public Recommend findByCoachName(String name) {
        Coach found = items.keySet().stream()
                .filter(coach -> coach.name().equals(name))
                .findFirst()
                .orElse(null);
        if (found == null) {
            throw new CustomException(String.format("%s은(는) 존재하지 않는 코치입니다.", name));
        }
        return items.get(found);
    }

    @Override
    public Recommend findByCoach(Coach coach) {
        Recommend recommend = items.get(coach);
        if (recommend == null) {
            return Recommend.getInit();
        }
        return recommend;
    }

    @Override
    public void save(Coach coach, Recommend recommend) {
        items.put(coach, recommend);
    }
}
