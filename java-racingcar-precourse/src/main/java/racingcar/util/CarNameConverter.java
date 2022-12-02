package racingcar.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarNameConverter {

    private CarNameConverter() {}

    public static List<String> convert(String input) {
        String[] split = input.split(",");
        List<String> result = Arrays.stream(split)
                .filter(CarNameConverter::isValidLength)
                .collect(Collectors.toList());
        validateResult(split, result);
        return result;
    }

    private static boolean isValidLength(String name) {
        return name.length() > 0 && name.length() < 6;
    }

    private static void validateResult(String[] input, List<String> result) {
        if (input.length != result.size()) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상, 5자 이하만 가능합니다.");
        }
    }

}
