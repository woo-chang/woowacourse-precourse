package vendingmachine.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoinStorage {

    private final Map<Coin, Integer> coinStorage;

    public CoinStorage(Map<Coin, Integer> coinStorage) {
        this.coinStorage = coinStorage;
    }

    public Map<Coin, Integer> getRemainedCoins(int money) {
        Map<Coin, Integer> result = new LinkedHashMap<>();
        for (Coin coin : Coin.values()) {
            if (isPossibleReturn(coin, money)) {
                money = calculateReturn(result, coin, money);
            }
        }
        return result;
    }

    private boolean isPossibleReturn(Coin coin, int money) {
        return coin.getAmount() < money && coinStorage.get(coin) > 0;
    }

    private int calculateReturn(Map<Coin, Integer> result, Coin coin, int money) {
        int count = Math.min(coinStorage.get(coin), money / coin.getAmount());
        result.put(coin, count);
        return money - (coin.getAmount() * count);
    }
}
