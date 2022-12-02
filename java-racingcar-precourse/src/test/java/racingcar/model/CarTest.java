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

    @DisplayName("자동차 현재 상태를 출력한다.")
    @Test
    void showCarState() {
        //given
        Car car = new Car("test");
        car.move();

        //when
        String result = car.toString();

        //then
        assertThat(result).contains("test", ":", "-");
    }

}