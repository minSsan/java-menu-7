# java-menu-precourse

> ⏰ 4H 30M

## 메뉴 추천 과정

1. 월요일에 추천할 카테고리 무작위 선정
2. 각 코치가 월요일에 먹을 메뉴 추천
3. (화, 수, 목, 금)에 대해 1,2 과정 반복

## 코치 관련 조건

- 코치 이름은 최소 2글자 ~ 최대 4글자
- 코치는 최소 2명, 최대 5명까지 식사를 함께 한다
- 각 코치는 최소 0개, 최대 2개의 못 먹는 메뉴가 존재함
  - 0개인 경우 빈 값 입력
  - 추천 불가능한 경우는 존재 X
- 한 주에 같은 카테고리는 최대 2회 고를 수 있다
- 각 코치에게 한 주에 중복되지 않는 메뉴를 추천해야 한다

```
예시)
구구: 비빔밥, 김치찌개, 쌈밥, 규동, 우동 → 한식을 3회 먹으므로 불가능
토미: 비빔밥, 비빔밥, 규동, 우동, 볶음면 → 한 코치가 같은 메뉴를 먹으므로 불가능
제임스: 비빔밥, 김치찌개, 스시, 가츠동, 짜장면 → 매일 다른 메뉴를 먹으므로 가능
포코: 비빔밥, 김치찌개, 스시, 가츠동, 짜장면 → 제임스와 메뉴가 같지만, 포코는 매번 다른 메뉴를 먹으므로 가능
```

## 메뉴 카테고리

```
일식: 규동, 우동, 미소시루, 스시, 가츠동, 오니기리, 하이라이스, 라멘, 오코노미야끼
한식: 김밥, 김치찌개, 쌈밥, 된장찌개, 비빔밥, 칼국수, 불고기, 떡볶이, 제육볶음
중식: 깐풍기, 볶음면, 동파육, 짜장면, 짬뽕, 마파두부, 탕수육, 토마토 달걀볶음, 고추잡채
아시안: 팟타이, 카오 팟, 나시고렝, 파인애플 볶음밥, 쌀국수, 똠얌꿍, 반미, 월남쌈, 분짜
양식: 라자냐, 그라탱, 뇨끼, 끼슈, 프렌치 토스트, 바게트, 스파게티, 피자, 파니니
```

- `Randoms.pickNumberInRange(1, 5)`의 결과가 1이면 일식, 2면 한식, 3이면 중식, 4면 아시안, 5면 양식을 추천해야 한다.
- 추천할 수 없는 카테고리인 경우 다시 `Randoms.pickNumberInRange()`를 통해 임의의 값을 생성해서 추천할 카테고리를 정해야 한다.

## 메뉴

- 추천할 메뉴는 정해진 카테고리에 있는 메뉴를 `Randoms.shuffle()`을 통해 임의의 순서로 섞은 후, 첫 번째 값을 사용해야 한다.
- 카테고리에 포함되는 메뉴 목록을 `List<String>` 형태로 준비한다.
  ```
  String menu = Randoms.shuffle(menus).get(0);
  ```
- 임의로 메뉴의 순서 또는 데이터를 변경하면 안 된다.
- `Randoms.shuffle()` 메서드의 인자로 전달되는 메뉴 데이터는, 최초에 제공한 목록을 그대로 전달해야 한다.
- 코치에게 추천할 메뉴를 정할 때 이미 추천한 메뉴, 먹지 못하는 메뉴도 포함된 리스트를 전달해야 한다.
- 추천할 수 없는 메뉴인 경우 다시 섞은 후 첫 번째 값을 사용해야 한다.

## 실행 예시

```
점심 메뉴 추천을 시작합니다.

코치의 이름을 입력해 주세요. (, 로 구분)
토미,제임스,포코

토미(이)가 못 먹는 메뉴를 입력해 주세요.
우동,스시

제임스(이)가 못 먹는 메뉴를 입력해 주세요.
뇨끼,월남쌈

포코(이)가 못 먹는 메뉴를 입력해 주세요.
마파두부,고추잡채

메뉴 추천 결과입니다.
[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]
[ 카테고리 | 한식 | 한식 | 일식 | 중식 | 아시안 ]
[ 토미 | 쌈밥 | 김치찌개 | 미소시루 | 짜장면 | 팟타이 ]
[ 제임스 | 된장찌개 | 비빔밥 | 가츠동 | 토마토 달걀볶음 | 파인애플 볶음밥 ]
[ 포코 | 된장찌개 | 불고기 | 하이라이스 | 탕수육 | 나시고렝 ]

추천을 완료했습니다.
```
