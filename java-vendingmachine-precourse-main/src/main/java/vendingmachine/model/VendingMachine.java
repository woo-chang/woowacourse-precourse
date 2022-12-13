package vendingmachine.model;

import java.util.Map;

public class VendingMachine {

    private final CoinStorage coinStorage;
    private final ProductStorage productStorage;
    private int moneyOfInput;

    public VendingMachine(CoinStorage coinStorage, ProductStorage productStorage,
            int moneyOfInput) {
        this.coinStorage = coinStorage;
        this.productStorage = productStorage;
        this.moneyOfInput = moneyOfInput;
    }

    public boolean isUsable() {
        return productStorage.canBuy(moneyOfInput);
    }

    public void use(String name) {
        int price = productStorage.buy(name);
        moneyOfInput -= price;
    }

    public Map<Coin, Integer> getRemainedCoins() {
        return coinStorage.getRemainedCoins(moneyOfInput);
    }

    public int getMoneyOfInput() {
        return moneyOfInput;
    }
}
