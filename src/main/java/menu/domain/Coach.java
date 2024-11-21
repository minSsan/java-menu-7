package menu.domain;

import menu.infrastructure.CustomException;

import java.util.ArrayList;
import java.util.List;

public record Coach(String name, List<Menu> hateMenus) {
    public Coach {
        validateName(name);
    }

    public static Coach of(String name) {
        return new Coach(name, new ArrayList<>());
    }

    public void addHateMenu(Menu menu) {
        hateMenus.add(menu);
    }

    private void validateName(String name) {
        if (name.length() < 2) {
            throw new CustomException("코치의 이름은 최소 두 글자 이상이어야 합니다.");
        }
        if (name.length() > 4) {
            throw new CustomException("코치의 이름은 최대 네 글자까지 가능합니다.");
        }
    }

    public boolean isHateMenu(Menu menu) {
        return hateMenus.stream().anyMatch(m -> m.equals(menu));
    }
}
