package subway.view;

import java.util.Scanner;
import subway.domain.MainMenu;
import subway.domain.SearchMenu;
import subway.domain.Station;
import subway.repository.StationRepository;

public class InputView {

    private static final String MENU_MESSAGE = "## 원하는 기능을 선택하세요.";
    private static final String START_STATION = "## 출발역을 입력하세요.";
    private static final String ARRIVAL_STATION = "## 도착역을 입력하세요.";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public MainMenu readMainMenu() {
        while (true) {
            try {
                OutputView.printMessage(MENU_MESSAGE);
                return MainMenu.from(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public SearchMenu readSearchMenu() {
        while (true) {
            try {
                OutputView.printMessage(MENU_MESSAGE);
                return SearchMenu.from(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public Station readStartStation() {
        while (true) {
            try {
                OutputView.printMessage(START_STATION);
                return StationRepository.findByName(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public Station readArrivalStation() {
        while (true) {
            try {
                OutputView.printMessage(ARRIVAL_STATION);
                return StationRepository.findByName(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
