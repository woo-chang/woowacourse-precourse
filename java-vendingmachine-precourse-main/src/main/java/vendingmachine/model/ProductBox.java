package vendingmachine.model;

import java.util.Map;

public class ProductBox {

    private final Map<String, ProductInfo> productBox;

    public ProductBox(Map<String, ProductInfo> productBox) {
        this.productBox = productBox;
    }
}
