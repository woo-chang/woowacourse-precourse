package vendingmachine.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumericConverterTest {

    @DisplayName("입력이 숫자 문자열이면 숫자로 변환된다.")
    @ValueSource(strings = {"-1", "0", "1", "100"})
    @ParameterizedTest
    void convert(String input) {
        int result = NumericConverter.convert(input);

        assertThat(result).isEqualTo(Integer.parseInt(input));
    }

    @DisplayName("입력이 숫자 문자열이 아니면 예외가 발생한다.")
    @ValueSource(strings = {" ", "a", "ㅁ", "!"})
    @ParameterizedTest
    void convertFail(String input) {
        assertThatThrownBy(() -> NumericConverter.convert(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}