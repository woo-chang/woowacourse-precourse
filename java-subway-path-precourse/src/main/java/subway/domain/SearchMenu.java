package subway.domain;

import java.util.Arrays;

public enum SearchMenu {

    DISTANCE("1", "최단 거리"),
    TIME("2", "최소 시간"),
    BACK("B", "돌아가기");

    SearchMenu(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;
    private final String name;

    public String getValue() {
        return value;
    }

    public static SearchMenu from(String value) {
        return Arrays.stream(SearchMenu.values())
                .filter(menu -> menu.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 조회 메뉴입니다."));
    }

    @Override
    public String toString() {
        return String.format("%s. %s", value, name);
    }
}
