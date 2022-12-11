package vendingmachine.model;

public class VendingMachine {

    private final CoinBox coinBox;
    private final ProductBox productBox;

    public VendingMachine(CoinBox coinBox, ProductBox productBox) {
        this.coinBox = coinBox;
        this.productBox = productBox;
    }
}
