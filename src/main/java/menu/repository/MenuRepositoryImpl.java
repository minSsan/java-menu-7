package menu.repository;

import menu.domain.Category;
import menu.domain.Menu;
import menu.infrastructure.CustomException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository {
    private final List<Menu> items;

    public MenuRepositoryImpl() {
        items = getInitMenus();
    }

    @Override
    public void save(Menu menu) {
        if (items.stream().anyMatch(m -> m.equals(menu))) {
            throw new CustomException("이미 존재하는 메뉴입니다.");
        }
        items.add(menu);
    }

    @Override
    public List<Menu> findAll() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public List<Menu> findByCategory(Category category) {
        return items.stream()
                .filter(menu -> menu.category() == category)
                .toList();
    }

    @Override
    public Menu findByName(String name) {
        return items.stream()
                .filter(menu -> menu.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    private List<Menu> getInitMenus() {
        List<Menu> result = new ArrayList<>();
        result.addAll(getJapanMenus());
        result.addAll(getKoreaMenus());
        result.addAll(getChinaMenus());
        result.addAll(getAsianMenus());
        result.addAll(getWesternMenus());
        return result;
    }

    private List<Menu> getJapanMenus() {
        return List.of(
                new Menu("규동", Category.일식),
                new Menu("우동", Category.일식),
                new Menu("미소시루", Category.일식),
                new Menu("스시", Category.일식),
                new Menu("가츠동", Category.일식),
                new Menu("오니기리", Category.일식),
                new Menu("하이라이스", Category.일식),
                new Menu("라멘", Category.일식),
                new Menu("오코노미야끼", Category.일식)
        );
    }

    private List<Menu> getKoreaMenus() {
        return List.of(
                new Menu("김밥", Category.한식),
                new Menu("김치찌개", Category.한식),
                new Menu("쌈밥", Category.한식),
                new Menu("된장찌개", Category.한식),
                new Menu("비빔밥", Category.한식),
                new Menu("칼국수", Category.한식),
                new Menu("불고기", Category.한식),
                new Menu("떡볶이", Category.한식),
                new Menu("제육볶음", Category.한식)
        );
    }

    private List<Menu> getChinaMenus() {
        return List.of(
                new Menu("깐풍기", Category.중식),
                new Menu("볶음면", Category.중식),
                new Menu("동파육", Category.중식),
                new Menu("짜장면", Category.중식),
                new Menu("짬뽕", Category.중식),
                new Menu("마파두부", Category.중식),
                new Menu("탕수육", Category.중식),
                new Menu("토마토 달걀볶음", Category.중식),
                new Menu("고추잡채", Category.중식)
        );
    }

    private List<Menu> getAsianMenus() {
        return List.of(
                new Menu("팟타이", Category.아시안),
                new Menu("카오 팟", Category.아시안),
                new Menu("나시고렝", Category.아시안),
                new Menu("파인애플 볶음밥", Category.아시안),
                new Menu("쌀국수", Category.아시안),
                new Menu("똠얌꿍", Category.아시안),
                new Menu("반미", Category.아시안),
                new Menu("월남쌈", Category.아시안),
                new Menu("분짜", Category.아시안)
        );
    }

    private List<Menu> getWesternMenus() {
        return List.of(
                new Menu("라자냐", Category.양식),
                new Menu("그라탱", Category.양식),
                new Menu("뇨끼", Category.양식),
                new Menu("끼슈", Category.양식),
                new Menu("프렌치 토스트", Category.양식),
                new Menu("바게트", Category.양식),
                new Menu("스파게티", Category.양식),
                new Menu("피자", Category.양식),
                new Menu("파니니", Category.양식)
        );
    }
}
