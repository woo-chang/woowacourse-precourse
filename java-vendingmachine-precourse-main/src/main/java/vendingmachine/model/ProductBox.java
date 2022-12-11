package vendingmachine.model;

import java.util.Map;

public class ProductBox {

    private final Map<String, ProductInfo> productBox;

    public ProductBox(Map<String, ProductInfo> productBox) {
        this.productBox = productBox;
    }

    public boolean canBuy(int money) {
        if (productBox.isEmpty()) {
            return false;
        }
        if (money < searchCheapestPrice()) {
            return false;
        }
        return true;
    }

    private int searchCheapestPrice() {
        return productBox.values().stream()
                .mapToInt(ProductInfo::getPrice)
                .min()
                .getAsInt();
    }

    public int buy(String name) {
        if (!productBox.containsKey(name)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        ProductInfo productInfo = productBox.get(name);
        ProductInfo update = productInfo.updateByPurchase();
        if (update.getCount() == 0) {
            productBox.remove(name);
        } else {
            productBox.put(name, update);
        }
        return update.getPrice();
    }
}