package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<String> readProducts() {
        String input = Console.readLine();
        return Arrays.stream(input.split(";"))
                .collect(Collectors.toList());
    }

    public int readMoneyOnInput() {
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
