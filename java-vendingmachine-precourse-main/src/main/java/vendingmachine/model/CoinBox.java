package vendingmachine.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoinBox {

    private final Map<Coin, Integer> stringBox;

    public CoinBox(Map<Coin, Integer> stringBox) {
        this.stringBox = stringBox;
    }

    public Map<Coin, Integer> getRemain(int money) {
        Map<Coin, Integer> result = new LinkedHashMap<>();
        for (Coin coin : Coin.values()) {
            if (coin.getAmount() < money && stringBox.get(coin) > 0) {
                int count = Math.min(stringBox.get(coin), money / coin.getAmount());
                result.put(coin, count);
                money -= coin.getAmount() * count;
            }
        }
        return result;
    }
}
