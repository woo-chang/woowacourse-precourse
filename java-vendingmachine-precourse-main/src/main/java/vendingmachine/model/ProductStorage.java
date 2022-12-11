package vendingmachine.model;

import java.util.Map;

public class ProductStorage {

    private final Map<String, ProductInfo> productBox;

    public ProductStorage(Map<String, ProductInfo> productBox) {
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
        validate(name);
        ProductInfo productInfo = productBox.get(name);
        ProductInfo update = productInfo.updateByPurchase();
        clearInventory(name, update);
        return update.getPrice();
    }

    private void validate(String name) {
        if (!productBox.containsKey(name)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
    }

    private void clearInventory(String name, ProductInfo update) {
        if (update.getCount() == 0) {
            productBox.remove(name);
        }
        if (update.getCount() != 0) {
            productBox.put(name, update);
        }
    }
}
