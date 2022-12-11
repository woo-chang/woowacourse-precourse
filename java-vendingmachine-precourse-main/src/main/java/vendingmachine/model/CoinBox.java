package vendingmachine.model;

import java.util.Map;

public class CoinBox {

    private final Map<Coin, Integer> stringBox;

    public CoinBox(Map<Coin, Integer> stringBox) {
        this.stringBox = stringBox;
    }
}
