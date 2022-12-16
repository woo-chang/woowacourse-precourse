package pairmatching.view;

public class OutputView {

    private static final String ERROR = "[ERROR] %s";

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(String.format(ERROR, message));
    }
}
