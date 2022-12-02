package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RacingGame {

    private static final int START_RANGE = 0;
    private static final int END_RANGE = 9;
    private static final int TARGET = 4;

    private final List<Car> cars;

    public RacingGame(List<Car> cars) {
        this.cars = cars;
    }

    public void play() {
        for (Car car : cars) {
            int random = Randoms.pickNumberInRange(START_RANGE, END_RANGE);
            if (canMove(random)) {
                car.move();
            }
        }
    }

    private boolean canMove(int number) {
        return number >= TARGET;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

}
