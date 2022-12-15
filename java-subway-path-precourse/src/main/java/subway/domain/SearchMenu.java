package subway.domain;

import java.util.Arrays;

public enum SearchMenu {

    DISTANCE("1"),
    TIME("2"),
    BACK("B");

    SearchMenu(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

    public static SearchMenu from(String value) {
        return Arrays.stream(SearchMenu.values())
                .filter(menu -> menu.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 조회 메뉴입니다."));
    }
}
