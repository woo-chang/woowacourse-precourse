package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    @DisplayName("우승한 자동차 목록을 반환한다.")
    @Test
    void getWinner() {
        //given
        Car car1 = new Car("test1");
        Car car2 = new Car("test2");
        Car car3 = new Car("test3");
        car2.move();
        car3.move();
        RacingGame racingGame = new RacingGame(List.of(car1, car2, car3));

        //when
        List<Car> result = racingGame.getWinner();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).containsExactly(car2, car3);
    }

}