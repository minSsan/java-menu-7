package menu.service;

import menu.domain.Coach;
import menu.domain.Menu;
import menu.infrastructure.CustomException;
import menu.repository.MenuRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    private final MenuRepository menuRepository;

    public InputParser(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> toMenus(String menuInput) {
        if (menuInput.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<String> menuNames = Arrays.stream(menuInput.split(","))
                .map(String::trim)
                .toList();
        List<Menu> result = new ArrayList<>();
        for (String name : menuNames) {
            Menu menu = menuRepository.findByName(name);
            if (menu == null) {
                throw new CustomException("존재하지 않는 메뉴입니다.");
            }
            result.add(menu);
        }
        return result;
    }

    public List<Coach> toCoaches(String coachInput) {
        List<String> coachNames = Arrays.stream(coachInput.split(","))
                .map(String::trim)
                .toList();
        if (coachNames.stream().anyMatch(String::isEmpty)) {
            throw new CustomException("코치 이름은 최소 한 글자 이상의 문자를 포함해야 합니다.");
        }
        if (coachNames.size() < 2) {
            throw new CustomException("코치는 최소 2명 이상 입력해야 합니다.");
        }
        if (coachNames.size() > 5) {
            throw new CustomException("코치는 최대 5명까지 입력해야 합니다.");
        }
        if (coachNames.stream().distinct().toList().size() != coachNames.size()) {
            throw new CustomException("중복되는 코치 이름이 존재합니다.");
        }
        List<Coach> result = new ArrayList<>();
        for (String coachName : coachNames) {
            result.add(Coach.of(coachName));
        }
        return result;
    }
}
