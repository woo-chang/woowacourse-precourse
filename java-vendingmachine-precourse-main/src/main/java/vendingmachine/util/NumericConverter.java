package vendingmachine.util;

public class NumericConverter {

    private NumericConverter() {
    }

    public static int convert(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
