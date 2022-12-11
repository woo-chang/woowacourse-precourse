package vendingmachine.view;

import java.util.Arrays;
import java.util.Map;
import vendingmachine.model.Coin;

public class OutputView {

    private final static String ERROR_PREFIX = "[ERROR] %s";

    public void printHoldingCoins(Map<Coin, Integer> coins) {
        System.out.println("자판기가 보유한 동전");
        Arrays.stream(Coin.values())
                .forEach(coin -> System.out.println(
                        String.format("%d원 - %d개", coin.getAmount(), coins.get(coin))));
    }

    public void printInputAmount(int money) {
        System.out.println(String.format("투입 금액: %d원", money));
    }

    public void printRemainCoins(Map<Coin, Integer> coins) {
        System.out.println("잔돈");
        for (Coin coin : Coin.values()) {
            if (coins.containsKey(coin)) {
                System.out.println(
                        String.format("%d원 - %d개", coin.getAmount(), coins.get(coin)));
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.printf((ERROR_PREFIX) + "%n", message);
    }
}
