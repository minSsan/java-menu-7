package menu.service;

import menu.domain.Coach;
import menu.domain.Menu;
import menu.domain.Recommend;
import menu.infrastructure.CustomException;
import menu.repository.CoachRepository;
import menu.repository.MenuRepository;
import menu.repository.RecommendRepository;

import java.util.List;

public class CoachService {
    private final CoachRepository coachRepository;
    private final RecommendRepository recommendRepository;
    private final MenuRepository menuRepository;

    public CoachService(
            CoachRepository coachRepository,
            RecommendRepository recommendRepository,
            MenuRepository menuRepository
    ) {
        this.coachRepository = coachRepository;
        this.recommendRepository = recommendRepository;
        this.menuRepository = menuRepository;
    }

    public void saveCoaches(List<Coach> coaches) {
        for (Coach coach : coaches) {
            coachRepository.save(coach);
            recommendRepository.save(coach, Recommend.getInit());
        }
    }

    public void setHateMenus(String name, List<Menu> menus) {
        Coach coach = coachRepository.findByName(name);
        if (coach == null) {
            throw new CustomException(String.format("%s은(는) 존재하지 않는 코치입니다.", name));
        }
        for (Menu menu : menus) {
            Menu found = menuRepository.findByName(menu.name());
            if (found == null) {
                throw new CustomException(String.format("%s은(는) 존재하지 않는 메뉴입니다.", menu.name()));
            }
            coach.addHateMenu(menu);
        }
    }

    public List<Coach> getAllCoach() {
        return coachRepository.findAll();
    }
}
