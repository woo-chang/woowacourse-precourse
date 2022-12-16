package subway.controller;

import java.util.Scanner;
import subway.domain.MainMenu;
import subway.domain.SearchMenu;
import subway.domain.Station;
import subway.service.SubwayService;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final SubwayService subwayService;

    public SubwayController(Scanner scanner) {
        inputView = new InputView(scanner);
        outputView = new OutputView();
        subwayService = new SubwayService();
    }

    public void process() {
        MainMenu mainMenu;
        do {
            outputView.printMainMenu();
            mainMenu = inputView.readMainMenu();
        } while (processMainMenu(mainMenu));
    }

    private boolean processMainMenu(MainMenu mainMenu) {
        if (mainMenu == MainMenu.ROUTE) {
            try {
                outputView.printSearchMenu();
                SearchMenu searchMenu = inputView.readSearchMenu();
                return processSearchMenu(searchMenu);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
                processMainMenu(mainMenu);
            }
        }
        return false;
    }

    private boolean processSearchMenu(SearchMenu searchMenu) {
        if (searchMenu == SearchMenu.BACK) {
            return true;
        }
        Station startStation = inputView.readStartStation();
        Station arrivalStation = inputView.readArrivalStation();
        if (searchMenu == SearchMenu.DISTANCE) {
            outputView.printSearchResult(
                    subwayService.findShortestDistance(startStation, arrivalStation));
        }
        if (searchMenu == SearchMenu.TIME) {
            outputView.printSearchResult(
                    subwayService.findShortestTime(startStation, arrivalStation));
        }
        return true;
    }
}
