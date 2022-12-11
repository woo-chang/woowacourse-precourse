package vendingmachine.model;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinStorageTest {

    @DisplayName("반환 가능한 잔돈을 확인한다.")
    @Test
    void getRemainedCoins() {
        Map<Coin, Integer> coinCount = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>(Coin.COIN_500, 0),
                        new SimpleEntry<>(Coin.COIN_100, 2),
                        new SimpleEntry<>(Coin.COIN_50, 3),
                        new SimpleEntry<>(Coin.COIN_10, 4)
                )
        );
        CoinStorage coinStorage = new CoinStorage(coinCount);
        int money = 400;

        Map<Coin, Integer> result = coinStorage.getRemainedCoins(money);

        Assertions.assertThat(result.get(Coin.COIN_100)).isEqualTo(2);
        Assertions.assertThat(result.get(Coin.COIN_50)).isEqualTo(3);
        Assertions.assertThat(result.get(Coin.COIN_10)).isEqualTo(4);
    }
}