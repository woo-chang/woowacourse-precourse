package vendingmachine.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vendingmachine.model.Coin;

public class CoinBoxGenerator {

    private static final int DIVIDE_PRICE = 10;
    private static final String ERROR_MESSAGE = "보유 금액은 0보다 크거나 같고, %d원으로 나누어떨어져야 합니다.";

    public Map<Coin, Integer> generate(int price) {
        validate(price);
        Map<Coin, Integer> result = initResults();
        List<Integer> cases = initCases();
        while (price != 0) {
            removeImpossibleCase(price, cases);
            price = storeCoin(price, result, cases);
        }
        return result;
    }

    private void validate(int price) {
        if (price < 0 || price % DIVIDE_PRICE != 0) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, DIVIDE_PRICE));
        }
    }

    private Map<Coin, Integer> initResults() {
        Map<Coin, Integer> result = new HashMap<>();
        Arrays.stream(Coin.values())
                .forEach(coin -> result.put(coin, 0));
        return result;
    }

    private List<Integer> initCases() {
        return Arrays.stream(Coin.values())
                .mapToInt(Coin::getAmount)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static void removeImpossibleCase(int price, List<Integer> cases) {
        while (cases.get(0) > price) {
            cases.remove(0);
        }
    }

    private int storeCoin(int price, Map<Coin, Integer> result, List<Integer> cases) {
        Coin coin = getCoin(cases);
        result.put(coin, result.getOrDefault(coin, 0) + 1);
        return price - coin.getAmount();
    }

    private Coin getCoin(List<Integer> cases) {
        int amount = Randoms.pickNumberInList(cases);
        return searchCoin(amount);
    }

    private Coin searchCoin(int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.getAmount() == amount)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 코인입니다."));
    }
}
