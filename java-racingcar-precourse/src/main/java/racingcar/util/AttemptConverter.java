package racingcar.util;

public class AttemptConverter {

    private static final String ERROR_MESSAGE = "시도 횟수는 숫자여야 한다.";

    private AttemptConverter() {
    }

    public static int convert(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE, e);
        }
    }

}
