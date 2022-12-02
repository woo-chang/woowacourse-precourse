package racingcar.util;

public class AttemptConverter {

    private static final String ERROR_MESSAGE_PARSE = "시도 횟수는 숫자여야 한다.";
    private static final String ERROR_MESSAGE_ATTEMPT = "시도 횟수는 0보다 커야합니다.";

    private AttemptConverter() {
    }

    public static int convert(String input) {
        try {
            int attempt = Integer.parseInt(input);
            validateAttempt(attempt);
            return attempt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PARSE, e);
        }
    }

    private static void validateAttempt(int attempt) {
        if (attempt <= 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ATTEMPT);
        }
    }

}
