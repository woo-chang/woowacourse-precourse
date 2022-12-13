package vendingmachine.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductStorageTest {

    @DisplayName("상품 저장소가 비어있으면 물건을 살 수 없다.")
    @Test
    void emptyProductStorage() {
        Map<String, ProductInfo> products = new HashMap<>();
        ProductStorage productStorage = new ProductStorage(products);
        int money = 3000;

        boolean result = productStorage.canBuy(money);

        assertThat(result).isFalse();
    }

    @DisplayName("상품 저장소 물건 중 최대 개수가 0개라면 물건을 살 수 없다.")
    @Test
    void maxCountZero() {
        Map<String, ProductInfo> products = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>("콜라", new ProductInfo(1500, 0)),
                        new SimpleEntry<>("사이다", new ProductInfo(1000, 0))
                )
        );
        ProductStorage productStorage = new ProductStorage(products);
        int money = 3000;

        boolean result = productStorage.canBuy(money);

        assertThat(result).isFalse();
    }

    @DisplayName("상품 저장소 물건 중 최소 가격이 투입 금액보다 크다면 물건을 살 수 없다.")
    @Test
    void underMinPrice() {
        Map<String, ProductInfo> products = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>("콜라", new ProductInfo(1500, 1)),
                        new SimpleEntry<>("사이다", new ProductInfo(1000, 1))
                )
        );
        ProductStorage productStorage = new ProductStorage(products);
        int money = 500;

        boolean result = productStorage.canBuy(money);

        assertThat(result).isFalse();
    }

    @DisplayName("투입 금액으로 물건을 살 수 있다.")
    @Test
    void canBuy() {
        Map<String, ProductInfo> products = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>("콜라", new ProductInfo(1500, 1)),
                        new SimpleEntry<>("사이다", new ProductInfo(1000, 1))
                )
        );
        ProductStorage productStorage = new ProductStorage(products);
        int money = 3000;

        boolean result = productStorage.canBuy(money);

        assertThat(result).isTrue();
    }

    @DisplayName("해당 상품이 존재하지 않으면 예외가 발생한다.")
    @Test
    void notExistProduct() {
        Map<String, ProductInfo> products = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>("콜라", new ProductInfo(1500, 1)),
                        new SimpleEntry<>("사이다", new ProductInfo(1000, 1))
                )
        );
        ProductStorage productStorage = new ProductStorage(products);
        String name = "환타";

        assertThatThrownBy(() -> productStorage.buy(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당 상품이 매진되었으면 예외가 발생한다.")
    @Test
    void soldOutProduct() {
        Map<String, ProductInfo> products = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>("콜라", new ProductInfo(1500, 0)),
                        new SimpleEntry<>("사이다", new ProductInfo(1000, 1))
                )
        );
        ProductStorage productStorage = new ProductStorage(products);
        String name = "콜라";

        assertThatThrownBy(() -> productStorage.buy(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당 상품을 구매한다.")
    @Test
    void buy() {
        Map<String, ProductInfo> products = new HashMap<>(
                Map.ofEntries(
                        new SimpleEntry<>("콜라", new ProductInfo(1500, 1)),
                        new SimpleEntry<>("사이다", new ProductInfo(1000, 1))
                )
        );
        ProductStorage productStorage = new ProductStorage(products);
        String name = "콜라";

        int result = productStorage.buy(name);

        assertThat(result).isEqualTo(1500);
    }
}