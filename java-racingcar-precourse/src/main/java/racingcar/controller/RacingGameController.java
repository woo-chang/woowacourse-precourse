package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;
import racingcar.model.RacingGame;
import racingcar.util.AttemptConverter;
import racingcar.util.CarNameConverter;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        List<String> carNames = generateCarNames();
        List<Car> cars = generateCars(carNames);
        int attempt = generateAttempt();
        RacingGame racingGame = new RacingGame(cars);
        racing(racingGame, attempt);
        outputView.printWinner(racingGame.getWinner());
    }

    private List<String> generateCarNames() {
        while (true) {
            try {
                String input = inputView.readCarNames();
                return CarNameConverter.convert(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Car> generateCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private int generateAttempt() {
        while (true) {
            try {
                String input = inputView.readAttempt();
                return AttemptConverter.convert(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void racing(RacingGame game, int attempt) {
        outputView.printMessage("실행 결과");
        for (int i = 0; i < attempt; i++) {
            game.play();
            outputView.printExecuteResult(game.getCars());
        }
    }

}
