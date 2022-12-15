package subway.view;

import java.util.Scanner;
import subway.domain.MainMenu;
import subway.domain.SearchMenu;

public class InputView {

    private static final String MENU_MESSAGE = "## 원하는 기능을 선택하세요.";

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

}
