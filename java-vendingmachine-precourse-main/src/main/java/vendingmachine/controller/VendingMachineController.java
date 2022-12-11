package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.model.Coin;
import vendingmachine.model.CoinStorage;
import vendingmachine.model.ProductBox;
import vendingmachine.model.ProductInfo;
import vendingmachine.model.VendingMachine;
import vendingmachine.service.CoinStorageGenerator;
import vendingmachine.service.ProductBoxGenerator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CoinStorageGenerator coinStorageGenerator = new CoinStorageGenerator();
    private final ProductBoxGenerator productBoxGenerator = new ProductBoxGenerator();

    public void run() {
        CoinStorage coinStorage = generateCoinBox();
        ProductBox productBox = generateProductBox();
        int inputMoney = generateOnInput();
        VendingMachine vendingMachine = new VendingMachine(coinStorage, productBox, inputMoney);
        outputView.printInputAmount(vendingMachine.getInputMoney());
        useUtilEnd(vendingMachine);
        outputView.printRemainCoins(vendingMachine.getRemain());
    }

    private CoinStorage generateCoinBox() {
        while (true) {
            try {
                Map<Coin, Integer> coinBox = getCoinBox();
                return new CoinStorage(coinBox);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<Coin, Integer> getCoinBox() {
        int money = inputView.readMoneyOnHand();
        Map<Coin, Integer> coinBox = coinStorageGenerator.generate(money);
        outputView.printHoldingCoins(coinBox);
        return coinBox;
    }

    private ProductBox generateProductBox() {
        while (true) {
            try {
                Map<String, ProductInfo> productBox = getProductBox();
                return new ProductBox(productBox);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<String, ProductInfo> getProductBox() {
        List<String> products = inputView.readProducts();
        Map<String, ProductInfo> productBox = productBoxGenerator.generate(products);
        return productBox;
    }

    private int generateOnInput() {
        return inputView.readMoneyOnInput();
    }

    private void useUtilEnd(VendingMachine vendingMachine) {
        while (vendingMachine.isUsable()) {
            try {
                useVendingMachine(vendingMachine);
                outputView.printInputAmount(vendingMachine.getInputMoney());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void useVendingMachine(VendingMachine vendingMachine) {
        String name = inputView.readProductName();
        vendingMachine.use(name);
    }
}
