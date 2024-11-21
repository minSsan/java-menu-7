package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.*;
import menu.infrastructure.CustomException;
import menu.repository.CoachRepository;
import menu.repository.DayCategoryRepository;
import menu.repository.MenuRepository;
import menu.repository.RecommendRepository;

import java.util.List;

public class MenuRecommendService {
    private final MenuRepository menuRepository;
    private final RecommendRepository recommendRepository;
    private final CoachRepository coachRepository;
    private final DayCategoryRepository dayCategoryRepository;

    public MenuRecommendService(
            MenuRepository menuRepository,
            RecommendRepository recommendRepository,
            CoachRepository coachRepository,
            DayCategoryRepository dayCategoryRepository
    ) {
        this.menuRepository = menuRepository;
        this.recommendRepository = recommendRepository;
        this.coachRepository = coachRepository;
        this.dayCategoryRepository = dayCategoryRepository;
    }

    public void setRecommend() {
        // 각 요일마다 진행
        for (DayOfWeek day : DayOfWeek.values()) {
            // 1. 카테고리 설정
            Category category = getNextCategory();
            dayCategoryRepository.save(day, category);
            // 2. 각 코치마다 추천 메뉴 설정
            setAllRecommend(day);
        }
    }

    public Recommend getRecommend(Coach coach) {
        Recommend recommend = recommendRepository.findByCoach(coach);
        if (recommend == null) {
            throw new CustomException(String.format("%s은(는) 존재하지 않는 코치입니다.", coach.name()));
        }
        return recommend;
    }

    public List<Category> getDaysCategories() {
        return dayCategoryRepository.findAllCategory();
    }

    private Category getNextCategory() {
        Category category = Category.of(Randoms.pickNumberInRange(1, 5));
        while (getCategoryCount(category) > 2) {
            category = Category.of(Randoms.pickNumberInRange(1, 5));
        }
        return category;
    }

    private int getCategoryCount(Category category) {
        List<Category> categories = dayCategoryRepository.findAllCategory();
        return categories.stream()
                .filter(c -> c.equals(category))
                .toList()
                .size();
    }

    private void setAllRecommend(DayOfWeek day) {
        Category category = dayCategoryRepository.findCategoryByDay(day);
        List<Coach> coaches = coachRepository.findAll();
        for (Coach coach : coaches) {
            Recommend recommend = recommendRepository.findByCoach(coach);
            Menu menu = getCoachMenu(coach, category);
            recommend.setRecommendMenu(day, menu);
            recommendRepository.save(coach, recommend);
        }
    }

    private Menu getCoachMenu(Coach coach, Category category) {
        Menu randomMenu = getRandomMenu(category);
        Recommend recommend = recommendRepository.findByCoach(coach);
        while (recommend.getMenuCount(randomMenu) != 0 || coach.isHateMenu(randomMenu)) {
            randomMenu = getRandomMenu(category);
            recommend = recommendRepository.findByCoach(coach);
        }
        return randomMenu;
    }

    private Menu getRandomMenu(Category category) {
        List<String> menuNames = menuRepository.findByCategory(category).stream()
                .map(Menu::name)
                .toList();

        String result = Randoms.shuffle(menuNames).getFirst();
        return menuRepository.findByName(result);
    }
}
