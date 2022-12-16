package subway.view;

import java.util.List;
import subway.domain.MainMenu;
import subway.domain.SearchMenu;
import subway.dto.SearchResult;

public class OutputView {

    private static final String ERROR = "[ERROR] %s";
    private static final String MAIN_MENU = "## 메인 화면";
    private static final String SEARCH_MENU = "## 경로 기준";
    private static final String SEARCH_RESULT = "## 조회 결과";
    private static final String SEARCH_MESSAGE = "[INFO] %s";
    private static final String DISTANCE = "총 거리: %dkm";
    private static final String TIME = "총 소요 시간: %d분";
    private static final String LINE = "---";

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

    public void printSearchResult(SearchResult searchResult) {
        System.out.println(SEARCH_RESULT);
        System.out.println(String.format(SEARCH_MESSAGE, LINE));
        System.out.println(
                String.format(SEARCH_MESSAGE, String.format(DISTANCE, searchResult.getDistance())));
        System.out.println(
                String.format(SEARCH_MESSAGE, String.format(TIME, searchResult.getTime())));
        System.out.println(String.format(SEARCH_MESSAGE, LINE));
        printStations(searchResult.getStations());
    }

    private void printStations(List<String> stations) {
        for (String station : stations) {
            System.out.println(String.format(SEARCH_MESSAGE, station));
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(String.format(ERROR, message));
    }
}
