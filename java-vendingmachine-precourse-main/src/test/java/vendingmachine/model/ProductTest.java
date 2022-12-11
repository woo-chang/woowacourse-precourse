package vendingmachine.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @DisplayName("이름이 공백으로만 이루어지면 예외가 발생한다.")
    @ValueSource(strings = {" ", "  ", "   "})
    @ParameterizedTest
    void blankName(String name) {
        assertThatThrownBy(() -> new Product(name, 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 null이면 예외가 발생한다.")
    @Test
    void nullName() {
        assertThatThrownBy(() -> new Product(null, 10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가격이 100원보다 작으면 예외가 발생한다.")
    @ValueSource(ints = {-10, 0, 10, 90})
    @ParameterizedTest
    void lowPrice(int price) {
        assertThatThrownBy(() -> new Product("커피", price))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가격이 10으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @ValueSource(ints = {101, 111, 1105})
    @ParameterizedTest
    void notDividedPrice(int price) {
        assertThatThrownBy(() -> new Product("커피", price))
                .isInstanceOf(IllegalArgumentException.class);
    }
}