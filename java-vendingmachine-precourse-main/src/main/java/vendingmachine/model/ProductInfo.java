package vendingmachine.model;

public class ProductInfo {

    private final int price;
    private final int count;

    public ProductInfo(int price, int count) {
        validatePrice(price);
        validateCount(count);
        this.price = price;
        this.count = count;
    }

    private void validatePrice(int price) {
        if (price < 100 || price % 10 != 0) {
            throw new IllegalArgumentException(
                    String.format("상품 가격은 %d원부터 시작하며, %d원으로 나누어떨어져야 합니다.", 100, 10));
        }
    }

    private void validateCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(String.format("상품 수량은 최소 0개 존재해야 합니다."));
        }
    }

    public ProductInfo updateByPurchase() {
        return new ProductInfo(price, count - 1);
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
