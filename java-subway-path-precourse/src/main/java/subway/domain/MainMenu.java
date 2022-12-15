package subway.domain;

import java.util.Arrays;

public enum MainMenu {

    ROUTE("1"),
    QUIT("Q");

    MainMenu(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

    public static MainMenu from(String value) {
        return Arrays.stream(MainMenu.values())
                .filter(menu -> menu.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메인 메뉴입니다."));
    }

}
