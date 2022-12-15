package subway.view;

import subway.domain.MainMenu;
import subway.domain.SearchMenu;

public class OutputView {

    private static final String ERROR = "[ERROR] %s";
    private static final String MAIN_MENU = "## 메인 화면";
    private static final String SEARCH_MENU = "## 경로 기준";

    public void printMainMenu() {
        System.out.println(MAIN_MENU);
        for (MainMenu menu : MainMenu.values()) {
            System.out.println(menu);
        }
    }

    public void printSearchMenu() {
        System.out.println(SEARCH_MENU);
        for (SearchMenu menu : SearchMenu.values()) {
            System.out.println(menu);
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(String.format(ERROR, message));
    }
}
