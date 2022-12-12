package vendingmachine.view;

import java.util.Arrays;
import java.util.Map;
import vendingmachine.model.Coin;

public class OutputView {

    private final static String ERROR_PREFIX = "[ERROR] %s";
    private final static String COIN_ON_HAND_MESSAGE = "자판기가 보유한 동전";
    private final static String COIN_FORMAT = "%d원 - %d개";
    private final static String MONEY_OF_INPUT_MESSAGE = "투입 금액: %d원";
    private final static String REMAINED_MESSAGE = "잔돈";

    public void printCoinOnHand(Map<Coin, Integer> coins) {
        System.out.println(COIN_ON_HAND_MESSAGE);
        Arrays.stream(Coin.values())
                .forEach(coin -> System.out.println(
                        String.format(COIN_FORMAT, coin.getAmount(), coins.get(coin))));
    }

    public void printMoneyOfInput(int money) {
        System.out.println(String.format(MONEY_OF_INPUT_MESSAGE, money));
    }

    public void printRemainedCoins(Map<Coin, Integer> coins) {
        System.out.println(REMAINED_MESSAGE);
        for (Coin coin : Coin.values()) {
            if (coins.containsKey(coin)) {
                System.out.println(
                        String.format(COIN_FORMAT, coin.getAmount(), coins.get(coin)));
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
