package vendingmachine.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    @DisplayName("자판기를 사용하고 나면 투입 금액이 감소한다.")
    @Test
    void use() {
        Map<Coin, Integer> coins = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>(Coin.COIN_500, 1),
                        new SimpleEntry<>(Coin.COIN_100, 1),
                        new SimpleEntry<>(Coin.COIN_50, 1),
                        new SimpleEntry<>(Coin.COIN_10, 1)
                )
        );
        CoinStorage coinStorage = new CoinStorage(coins);
        Map<String, ProductInfo> products = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>("콜라", new ProductInfo(1500, 1)),
                        new SimpleEntry<>("사이다", new ProductInfo(1000, 1))
                )
        );
        ProductStorage productStorage = new ProductStorage(products);
        int moneyOfInput = 3000;
        VendingMachine vendingMachine = new VendingMachine(coinStorage, productStorage,
                moneyOfInput);
        String name = "콜라";

        vendingMachine.use(name);

        assertThat(vendingMachine.getMoneyOfInput()).isEqualTo(1500);
    }
}