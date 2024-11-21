package menu.infrastructure;

import menu.controller.MenuRecommendController;
import menu.repository.*;
import menu.service.CoachService;
import menu.service.InputParser;
import menu.service.MenuRecommendService;
import menu.view.InputView;
import menu.view.OutputView;

public class AppConfig {
    private CoachRepository coachRepository;
    private DayCategoryRepository dayCategoryRepository;
    private MenuRepository menuRepository;
    private RecommendRepository recommendRepository;

    public MenuRecommendController controller() {
        return new MenuRecommendController(
                inputParser(),
                coachService(),
                menuRecommendService(),
                inputView(),
                outputView()
        );
    }

    private CoachRepository coachRepository() {
        if (this.coachRepository == null) {
            this.coachRepository = new CoachRepositoryImpl();
        }
        return this.coachRepository;
    }

    private MenuRepository menuRepository() {
        if (this.menuRepository == null) {
            this.menuRepository = new MenuRepositoryImpl();
        }
        return this.menuRepository;
    }

    private DayCategoryRepository dayCategoryRepository() {
        if (this.dayCategoryRepository == null) {
            this.dayCategoryRepository = new DayCategoryRepositoryImpl();
        }
        return this.dayCategoryRepository;
    }

    private RecommendRepository recommendRepository() {
        if (this.recommendRepository == null) {
            this.recommendRepository = new RecommendRepositoryImpl();
        }
        return this.recommendRepository;
    }

    private InputParser inputParser() {
        return new InputParser(menuRepository());
    }

    private CoachService coachService() {
        return new CoachService(
                coachRepository(),
                recommendRepository(),
                menuRepository()
        );
    }

    private MenuRecommendService menuRecommendService() {
        return new MenuRecommendService(
                menuRepository(),
                recommendRepository(),
                coachRepository(),
                dayCategoryRepository()
        );
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
