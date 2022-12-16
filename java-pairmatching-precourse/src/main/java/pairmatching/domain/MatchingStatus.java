package pairmatching.domain;

import java.util.Arrays;

public enum MatchingStatus {

    YES("네"),
    NO("아니오");

    MatchingStatus(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

    public static MatchingStatus from(String value) {
        return Arrays.stream(MatchingStatus.values())
                .filter(matchingStatus -> matchingStatus.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매칭 여부입니다."));
    }
}
