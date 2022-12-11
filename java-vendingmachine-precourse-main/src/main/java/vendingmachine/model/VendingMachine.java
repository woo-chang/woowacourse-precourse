package vendingmachine.model;

import java.util.Map;

public class VendingMachine {

    private final CoinStorage coinStorage;
    private final ProductStorage productStorage;
    private int inputMoney;

    public VendingMachine(CoinStorage coinStorage, ProductStorage productStorage, int inputMoney) {
        this.coinStorage = coinStorage;
        this.productStorage = productStorage;
        this.inputMoney = inputMoney;
    }

    public boolean isUsable() {
        return productStorage.canBuy(inputMoney);
    }

    public void use(String name) {
        int price = productStorage.buy(name);
        inputMoney -= price;
    }

    public Map<Coin, Integer> getRemain() {
        return coinStorage.getRemain(inputMoney);
    }

    public int getInputMoney() {
        return inputMoney;
    }
}
