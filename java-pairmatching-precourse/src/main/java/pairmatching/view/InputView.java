package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Function;

public class InputView {

    private static final String FUNCTION_MESSAGE = "기능을 선택하세요.";

    public Function readFunction() {
        try {
            explainReadFunction();
            String input = Console.readLine();
            return Function.from(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readFunction();
        }
    }

    private void explainReadFunction() {
        OutputView.printMessage(FUNCTION_MESSAGE);
        for (Function value : Function.values()) {
            OutputView.printMessage(value.toString());
        }
    }
}
