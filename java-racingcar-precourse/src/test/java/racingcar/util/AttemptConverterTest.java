package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AttemptConverterTest {

    @DisplayName("시도 횟수를 숫자로 변환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "100"})
    void covert(String input) {
        //when
        int result = AttemptConverter.convert(input);

        //then
        assertThat(result).isEqualTo(Integer.parseInt(input));
    }

    @DisplayName("시도 횟수가 숫자가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "가", "!", "A"})
    void convertWithException(String input) {
        //then
        assertThatThrownBy(() -> AttemptConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}