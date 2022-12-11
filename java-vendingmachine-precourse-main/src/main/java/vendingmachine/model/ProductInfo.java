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

    private void validateName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("상품 이름은 존재해야 합니다.");
        }
    }

    private void validatePrice(int price) {
        if (price < 100 || price % 10 != 0) {
            throw new IllegalArgumentException(
                    String.format("상품 가격은 %d원부터 시작하며, %d원으로 나누어떨어져야 합니다.", 100, 10));
        }
    }

    private void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(String.format("상품 수량은 최소 1개 존재해야 합니다."));
        }
    }
}
