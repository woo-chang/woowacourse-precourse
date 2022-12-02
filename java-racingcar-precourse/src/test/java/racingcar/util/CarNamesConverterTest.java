package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarNamesConverterTest {

    @DisplayName("쉼표로 구분된 이름을 정상적으로 변환한다.")
    @Test
    void convert() {
        //given
        String input = "pobi,woni,jun";

        //when
        List<String> result = CarNamesConverter.convert(input);

        //then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).containsExactly("pobi", "woni", "jun");
    }

    @DisplayName("유효하지 않은 이름이 존재하는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,,jun", "pobi,wonijun", "  ,   ", " "})
    void convertWithException(String input) {
        //then
        assertThatThrownBy(() -> CarNamesConverter.convert(input)).isInstanceOf(
                IllegalArgumentException.class);
    }

}