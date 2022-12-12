package vendingmachine.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.model.ProductInfo;

class ProductStorageGeneratorTest {

    private final ProductStorageGenerator productBoxGenerator = new ProductStorageGenerator();

    @DisplayName("상품 정보를 올바르게 저장한다.")
    @Test
    void storeProduct() {
        List<String> infos = new ArrayList<>(
                List.of("[콜라,1500,20]", "[사이다,1000,10]")
        );

        Map<String, ProductInfo> productBox = productBoxGenerator.generate(infos);

        assertThat(productBox.values().size()).isEqualTo(2);
    }

    @DisplayName("상품 이름에 공백으로만 이루어졌으면 예외가 발생한다.")
    @Test
    void invalidProductName() {
        List<String> infos = new ArrayList<>(
                List.of("[ ,1500,20]", "[   ,1000,10]")
        );

        assertThatThrownBy(() -> productBoxGenerator.generate(infos))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 상품을 입력하면 예외가 발생한다.")
    @Test
    void duplicateProduct() {
        List<String> infos = new ArrayList<>(
                List.of("[콜라,1500,20]", "[콜라,1000,10]")
        );

        assertThatThrownBy(() -> productBoxGenerator.generate(infos))
                .isInstanceOf(IllegalArgumentException.class);
    }
}