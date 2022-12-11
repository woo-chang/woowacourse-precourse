package vendingmachine.model;

import java.util.Map;

public class VendingMachine {

    private final CoinStorage coinStorage;
    private final ProductBox productBox;
    private int inputMoney;

    public VendingMachine(CoinStorage coinStorage, ProductBox productBox, int inputMoney) {
        this.coinStorage = coinStorage;
        this.productBox = productBox;
        this.inputMoney = inputMoney;
    }

    public boolean isUsable() {
        return productBox.canBuy(inputMoney);
    }

    public void use(String name) {
        int price = productBox.buy(name);
        inputMoney -= price;
    }

    public Map<Coin, Integer> getRemain() {
        return coinStorage.getRemain(inputMoney);
    }

    public int getInputMoney() {
        return inputMoney;
    }
}
