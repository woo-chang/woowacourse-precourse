package racingcar.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarNamesConverter {

    private static final String ERROR_MESSAGE = "자동차 이름은 공백 제외 1자 이상, 5자 이하만 가능합니다.";

    private CarNamesConverter() {
    }

    public static List<String> convert(String input) {
        String[] split = input.split(",");
        List<String> result = Arrays.stream(split)
                .map(String::trim)
                .filter(CarNamesConverter::isValidLength)
                .collect(Collectors.toList());
        validateResult(split, result);
        return result;
    }

    private static boolean isValidLength(String name) {
        return name.length() > 0 && name.length() < 6;
    }

    private static void validateResult(String[] input, List<String> result) {
        if (input.length != result.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

}
