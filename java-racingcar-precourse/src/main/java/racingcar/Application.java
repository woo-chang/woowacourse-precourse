package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RacingGameController racingGameController = new RacingGameController(inputView, outputView);
        racingGameController.execute();
    }
}
