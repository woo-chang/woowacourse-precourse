package racingcar.util;

public class AttemptConverter {

    private AttemptConverter() {}

    public static int convert(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.", e);
        }
    }

}
