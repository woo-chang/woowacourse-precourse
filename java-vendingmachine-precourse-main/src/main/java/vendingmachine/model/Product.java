package vendingmachine.model;

public class Product {

    private final String name;
    private final int price;

    public Product(String name, int price) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    private void validateName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("상품 이름은 존재해야 합니다.");
        }
    }

    private void validatePrice(int price) {
        if (price < 100 || price % 10 != 0) {
            throw new IllegalArgumentException(
                    String.format("상품 가격은 %d원부터 시자하며, %d원으로 나누어떨어져야 합니다.", 100, 10));
        }
    }
}
