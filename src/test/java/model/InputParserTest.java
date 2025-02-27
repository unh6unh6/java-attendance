package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputParserTest {

    private InputParser inputParser;

    @BeforeEach
    void setInputParser() {
        inputParser = new InputParser();
    }

    @DisplayName("시간 문자열을 LocalTime으로 변환한다")
    @Test
    void timeParseTest() {
        assertThat(inputParser.parseTimeInput("11:50")).hasHour(11).hasMinute(50);
    }

    @DisplayName("시간 문자열의 포맷이 올바르지 않으면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"11시50분", "11-50", "25:00", "10:60"})
    void wrongTimeFormatParseTest(final String input) {
        assertThatThrownBy(() -> inputParser.parseTimeInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바르지 않은 시간 입력 형식입니다.");
    }
}
