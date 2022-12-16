package pairmatching.view;

public enum OutputMessage {

    ERROR("[ERROR] %s"),
    LINE("#############################################"),
    COURSE("과정: %s"),
    MISSION("미션:"),
    LEVEL_PER_MISSION("  - %s: %s"),
    MATCHING_RESULT("페어 매칭 결과입니다."),
    FUNCTION("기능을 선택하세요."),
    SELECT("과정, 레벨, 미션을 선택하세요."),
    SELECT_EXAMPLE("ex) 백엔드, 레벨1, 자동차경주"),
    REMATCH("매칭 정보가 있습니다. 다시 매칭하시겠습니까?"),
    CLEAR("초기화 되었습니다.");

    OutputMessage(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
