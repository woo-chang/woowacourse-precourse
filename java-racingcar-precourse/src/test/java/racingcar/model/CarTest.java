package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @DisplayName("자동차 생성 시 위치는 0에 위치한다.")
    @Test
    void initCar() {
        //given
        Car car = new Car("test");

        //when
        int result = car.getPosition();

        //then
        assertThat(result).isEqualTo(0);
    }

    @DisplayName("자동차 전진 시 위치가 1 증가한다.")
    @Test
    void moveCar() {
        //given
        Car car = new Car("test");

        //when
        car.move();
        int result = car.getPosition();

        //then
        assertThat(result).isEqualTo(1);
    }

}