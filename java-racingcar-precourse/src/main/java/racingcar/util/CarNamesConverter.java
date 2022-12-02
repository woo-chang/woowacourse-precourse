package racingcar.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarNamesConverter {

    private static final String ERROR_MESSAGE = "자동차 이름은 공백 제외 1자 이상, 5자 이하만 가능합니다.";
    private static final String SEPARATOR = ",";

    private CarNamesConverter() {
    }

    public static List<String> convert(String input) {
        String[] target = input.split(SEPARATOR);
        List<String> names = Arrays.stream(target)
                .map(String::trim)
                .filter(CarNamesConverter::isValidLength)
                .collect(Collectors.toList());
        validateNames(target, names);
        return names;
    }

    private static boolean isValidLength(String name) {
        return name.length() > 0 && name.length() < 6;
    }

    private static void validateNames(String[] input, List<String> result) {
        if (input.length != result.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

}
