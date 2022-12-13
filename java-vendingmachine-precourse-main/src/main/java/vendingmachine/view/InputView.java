package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.util.NumericConverter;

public class InputView {

    private static final String COIN_ON_HAND_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String PRODUCTS_MESSAGE = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String MONEY_ON_INPUT_MESSAGE = "투입 금액을 입력해 주세요.";
    private static final String PRODUCT_NAME_MESSAGE = "구매할 상품명을 입력해 주세요.";

    public int readCoinOnHand() {
        while (true) {
            try {
                String input = inputCoinOnHand();
                return NumericConverter.convert(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private String inputCoinOnHand() {
        OutputView.printMessage(COIN_ON_HAND_MESSAGE);
        return Console.readLine();
    }

    public List<String> readProducts() {
        OutputView.printMessage(PRODUCTS_MESSAGE);
        String input = Console.readLine();
        return Arrays.stream(input.split(";"))
                .collect(Collectors.toList());
    }

    public int readMoneyOfInput() {
        while (true) {
            try {
                String input = inputMoneyOnInput();
                return NumericConverter.convert(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private String inputMoneyOnInput() {
        OutputView.printMessage(MONEY_ON_INPUT_MESSAGE);
        return Console.readLine();
    }

    public String readProductName() {
        OutputView.printMessage(PRODUCT_NAME_MESSAGE);
        return Console.readLine();
    }
}
