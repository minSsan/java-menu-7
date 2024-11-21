package menu.view;

import menu.domain.Category;
import menu.domain.DayOfWeek;
import menu.domain.Menu;
import menu.domain.Recommend;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class OutputView {
    public void printStart() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printEnd() {
        System.out.println("추천을 완료했습니다.");
    }

    public void printRecommendTitle() {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
    }

    public void printRecommendCategory(List<Category> categories) {
        List<String> names = categories.stream().map(Category::getName).toList();
        String joined = String.join(" | ", names);
        System.out.println(String.format("[ 카테고리 | %s ]", joined));
    }

    public void printRecommendRow(String coach, Recommend recommend) {
        EnumMap<DayOfWeek, Menu> recommends = recommend.recommends();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("[ %s | ", coach));

        List<String> menus = new ArrayList<>();
        menus.add(recommends.get(DayOfWeek.월).name());
        menus.add(recommends.get(DayOfWeek.화).name());
        menus.add(recommends.get(DayOfWeek.수).name());
        menus.add(recommends.get(DayOfWeek.목).name());
        menus.add(recommends.get(DayOfWeek.금).name());

        String joined = String.join(" | ", menus);
        stringBuffer.append(joined);
        stringBuffer.append(" ]");

        System.out.println(stringBuffer);
    }
}
