package racingcar.model;

import java.util.StringJoiner;

public class Car {

    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    // 추가 기능 구현
    public void move() {
        position += 1;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(name);
        joiner.add(":");
        joiner.add("-".repeat(position));
        return joiner.toString();
    }
}
