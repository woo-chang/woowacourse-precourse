package vendingmachine.model;

import java.util.Map;

public class VendingMachine {

    private final CoinBox coinBox;
    private final ProductBox productBox;
    private int inputMoney;

    public VendingMachine(CoinBox coinBox, ProductBox productBox, int inputMoney) {
        this.coinBox = coinBox;
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
        return coinBox.getRemain(inputMoney);
    }

    public int getInputMoney() {
        return inputMoney;
    }
}
