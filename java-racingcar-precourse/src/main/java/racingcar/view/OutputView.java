package racingcar.view;

import java.util.List;

public class OutputView {

    public void printExecuteResult(List<String> cars) {
        cars.forEach(System.out::println);
    }

}
