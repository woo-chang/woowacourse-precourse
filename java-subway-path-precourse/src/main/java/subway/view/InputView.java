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

    public MainMenu readMainMenu(Scanner scanner) {
        while (true) {
            try {
                OutputView.printMessage(MENU_MESSAGE);
                return MainMenu.from(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public SearchMenu readSearchMenu(Scanner scanner) {
        while (true) {
            try {
                OutputView.printMessage(MENU_MESSAGE);
                return SearchMenu.from(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public Station readStartStation(Scanner scanner) {
        while (true) {
            try {
                OutputView.printMessage(START_STATION);
                return StationRepository.findByName(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public Station readArrivalStation(Scanner scanner) {
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
