package vendingmachine.model;

import java.util.Map;

public class ProductStorage {

    private final Map<String, ProductInfo> productStorage;

    public ProductStorage(Map<String, ProductInfo> productStorage) {
        this.productStorage = productStorage;
    }

    public boolean canBuy(int money) {
        if (productStorage.isEmpty() || searchMaxCount() == 0) {
            return false;
        }
        if (money < searchMinPrice()) {
            return false;
        }
        return true;
    }

    private int searchMaxCount() {
        return productStorage.values().stream()
                .mapToInt(ProductInfo::getCount)
                .max()
                .getAsInt();
    }

    private int searchMinPrice() {
        return productStorage.values().stream()
                .mapToInt(ProductInfo::getPrice)
                .min()
                .getAsInt();
    }

    public int buy(String name) {
        validate(name);
        ProductInfo productInfo = productStorage.get(name);
        ProductInfo update = productInfo.updateByPurchase();
        productStorage.put(name, update);
        return update.getPrice();
    }

    private void validate(String name) {
        if (!productStorage.containsKey(name)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        if (productStorage.get(name).getCount() == 0) {
            throw new IllegalArgumentException("매진된 상품입니다.");
        }
    }
}
