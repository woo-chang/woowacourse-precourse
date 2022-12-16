package pairmatching.domain;

import java.util.Arrays;

public enum Function {

    MATCHING("1", "페어 매칭"),
    SEARCH("2", "페어 조회"),
    CLEAR("3", "페어 초기화"),
    QUIT("Q", "종료");

    Function(String value, String explain) {
        this.value = value;
        this.explain = explain;
    }

    private final String value;
    private final String explain;

    public static Function from(String value) {
        return Arrays.stream(Function.values())
                .filter(function -> function.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기능입니다."));
    }
}
