package vendingmachine.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductInfoTest {

    @DisplayName("가격이 100원보다 작으면 예외가 발생한다.")
    @ValueSource(ints = {-10, 0, 10, 90})
    @ParameterizedTest
    void lowPrice(int price) {
        int count = 1;

        assertThatThrownBy(() -> new ProductInfo(price, count))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가격이 10으로 나누어떨어지지 않으면 예외가 발생한다.")
    @ValueSource(ints = {101, 111, 1105})
    @ParameterizedTest
    void notDividedPrice(int price) {
        int count = 1;

        assertThatThrownBy(() -> new ProductInfo(price, count))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수량이 0개보다 작으면 예외가 발생한다.")
    @ValueSource(ints = {-10, -5, -1})
    @ParameterizedTest
    void negativeCount(int count) {
        int price = 1000;

        assertThatThrownBy(() -> new ProductInfo(price, count))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("업데이트하고 나면 수량이 1개 줄어든다.")
    @ValueSource(ints = {1, 5, 10})
    @ParameterizedTest
    void updateProductInfo(int count) {
        int price = 1000;
        ProductInfo before = new ProductInfo(price, count);

        ProductInfo update = before.updateByPurchase();

        assertThat(update.getCount()).isEqualTo(before.getCount() - 1);
    }
}