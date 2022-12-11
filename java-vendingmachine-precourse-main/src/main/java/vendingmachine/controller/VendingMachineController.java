package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.model.Coin;
import vendingmachine.model.CoinStorage;
import vendingmachine.model.ProductInfo;
import vendingmachine.model.ProductStorage;
import vendingmachine.model.VendingMachine;
import vendingmachine.service.CoinStorageGenerator;
import vendingmachine.service.ProductStorageGenerator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CoinStorageGenerator coinStorageGenerator = new CoinStorageGenerator();
    private final ProductStorageGenerator productStorageGenerator = new ProductStorageGenerator();

    public void run() {
        VendingMachine vendingMachine = generateVendingMachine();
        outputView.printMoneyOfInput(vendingMachine.getMoneyOfInput());
        useUtilEnd(vendingMachine);
        outputView.printRemainedCoins(vendingMachine.getRemainedCoins());
    }

    private VendingMachine generateVendingMachine() {
        CoinStorage coinStorage = generateCoinStorage();
        ProductStorage productStorage = generateProductStorage();
        int moneyOfInput = inputView.readMoneyOfInput();
        return new VendingMachine(coinStorage, productStorage, moneyOfInput);
    }

    private CoinStorage generateCoinStorage() {
        while (true) {
            try {
                Map<Coin, Integer> coinStorage = getCoinStorage();
                return new CoinStorage(coinStorage);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<Coin, Integer> getCoinStorage() {
        int coin = inputView.readCoinOnHand();
        Map<Coin, Integer> coinStorage = coinStorageGenerator.generate(coin);
        outputView.printCoinOnHand(coinStorage);
        return coinStorage;
    }

    private ProductStorage generateProductStorage() {
        while (true) {
            try {
                Map<String, ProductInfo> productStorage = getProductStorage();
                return new ProductStorage(productStorage);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<String, ProductInfo> getProductStorage() {
        List<String> products = inputView.readProducts();
        return productStorageGenerator.generate(products);
    }

    private void useUtilEnd(VendingMachine vendingMachine) {
        while (vendingMachine.isUsable()) {
            try {
                useVendingMachine(vendingMachine);
                outputView.printMoneyOfInput(vendingMachine.getMoneyOfInput());
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
