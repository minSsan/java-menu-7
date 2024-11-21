package menu.repository;

import menu.domain.Coach;
import menu.domain.Recommend;

public interface RecommendRepository {
    Recommend findByCoachName(String name);
    Recommend findByCoach(Coach coach);
    void save(Coach coach, Recommend recommend);
}
