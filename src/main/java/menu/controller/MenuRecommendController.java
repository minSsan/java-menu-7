package menu.controller;

import menu.domain.Coach;
import menu.domain.Menu;
import menu.domain.Recommend;
import menu.infrastructure.CustomException;
import menu.service.CoachService;
import menu.service.InputParser;
import menu.service.MenuRecommendService;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.List;

public class MenuRecommendController {
    private final InputParser inputParser;
    private final CoachService coachService;
    private final MenuRecommendService menuRecommendService;
    private final InputView inputView;
    private final OutputView outputView;

    public MenuRecommendController(
            InputParser inputParser,
            CoachService coachService,
            MenuRecommendService menuRecommendService,
            InputView inputView,
            OutputView outputView
    ) {
        this.inputParser = inputParser;
        this.coachService = coachService;
        this.menuRecommendService = menuRecommendService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 1. 점심메뉴 추천 시작 안내 문구 출력
        outputView.printStart();
        // 2. 코치 이름 입력
        List<Coach> coaches = getCoaches();
        // 3. 각 코치별 못 먹는 메뉴 입력
        for (Coach coach : coaches) {
            setHateMenus(coach.name());
        }
        // 4. 추천 메뉴 설정
        menuRecommendService.setRecommend();
        // 5. 결과 출력
        outputView.printRecommendTitle();
        outputView.printRecommendCategory(menuRecommendService.getDaysCategories());
        for (Coach coach : coaches) {
            Recommend recommend = menuRecommendService.getRecommend(coach);
            outputView.printRecommendRow(coach.name(), recommend);
        }
        outputView.printEnd();
    }

    private List<Coach> getCoaches() {
        while (true) {
            try {
                String coachNames = inputView.inputCoachNames();
                List<Coach> coaches = inputParser.toCoaches(coachNames);
                coachService.saveCoaches(coaches);
                return coaches;
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setHateMenus(String name) {
        while (true) {
            try {
                String inputHateMenus = inputView.inputHateMenus(name);
                List<Menu> menus = inputParser.toMenus(inputHateMenus);
                coachService.setHateMenus(name, menus);
                return;
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
