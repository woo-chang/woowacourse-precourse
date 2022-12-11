package vendingmachine.util;

public class NumericConverter {

    private final static String ERROR_MESSAGE = "금액은 숫자여야 합니다.";

    private NumericConverter() {
    }

    public static int convert(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE, e);
        }
    }
}
