package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.util.NumericConverter;

public class InputView {

    public int readMoneyOnHand() {
        while (true) {
            try {
                String input = Console.readLine();
                return NumericConverter.convert(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
