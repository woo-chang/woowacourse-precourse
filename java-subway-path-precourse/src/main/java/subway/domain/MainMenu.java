package subway.domain;

import java.util.Arrays;

public enum MainMenu {

    ROUTE("1", "경로 조회"),
    QUIT("Q", "종료");

    MainMenu(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;
    private final String name;

    public static MainMenu from(String value) {
        return Arrays.stream(MainMenu.values())
                .filter(menu -> menu.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메인 메뉴입니다."));
    }

    @Override
    public String toString() {
        return String.format("%s. %s", value, name);
    }
}
