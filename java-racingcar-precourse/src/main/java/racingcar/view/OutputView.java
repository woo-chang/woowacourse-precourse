package racingcar.view;

import java.util.List;
import java.util.StringJoiner;
import racingcar.model.Car;

public class OutputView {

    private static final String WINNER = "최종 우승자 : %s";

    public void printExecuteResult(List<Car> cars) {
        cars.forEach(System.out::println);
    }

    public void printWinner(List<Car> winners) {
        StringJoiner joiner = new StringJoiner(", ");
        winners.forEach(winner -> joiner.add(winner.getName()));
        System.out.printf((WINNER) + "%n", joiner.toString());
    }

}