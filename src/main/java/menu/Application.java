package menu;

import menu.controller.MenuRecommendController;
import menu.infrastructure.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MenuRecommendController controller = appConfig.controller();
        controller.run();
    }
}
