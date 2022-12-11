package vendingmachine.view;

public class OutputView {

    private final static String ERROR_PREFIX = "[ERROR] %s";

    public static void printErrorMessage(String message) {
        System.out.printf((ERROR_PREFIX) + "%n", message);
    }
}
