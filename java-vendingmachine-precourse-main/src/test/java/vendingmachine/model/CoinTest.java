package vendingmachine.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinTest {

    @DisplayName("해당하는 금액을 반환한다.")
    @Test
    void getAmount() {
        Coin coin = Coin.COIN_500;

        int result = coin.getAmount();

        assertThat(result).isEqualTo(500);
    }
}