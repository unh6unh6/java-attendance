package util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringParserTest {

    private StringParser stringParser;

    @BeforeEach
    void setUp() {
        stringParser = new StringParser();
    }

    @DisplayName("시간 문자열을 LocalTime으로 변환한다")
    @Test
    void parseLocalTimeTest() {
        // Given

        // When
        LocalTime parsedLocalTime = stringParser.parseLocalTime("11:11");

        // Then
        Assertions.assertThat(parsedLocalTime).hasHour(11).hasMinute(11);
    }

    @DisplayName("시간 형식이 HH:MM가 아니라면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"11%12", "25:12"})
    void invalidLocalTimeFormatTest(String input) {
        // Given

        // When & Then
        assertThatThrownBy(() -> stringParser.parseLocalTime(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 시간이 HH:MM 형식에 맞지 않습니다.");
    }

    @DisplayName("정수 문자열을 정수로 변환한다")
    @Test
    void parseIntTest() {
        // Given
        final String input = "11";

        // When
        int parsedInt = stringParser.parseInt(input);

        // Then
        Assertions.assertThat(parsedInt).isEqualTo(11);
    }

    @DisplayName("정수 문자열이 아니라면 예외가 반환한다")
    @Test
    void invalidIntFormatTest() {
        // Given
        final String input = "11일";

        // When & Then
        Assertions.assertThatThrownBy(() -> stringParser.parseInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 정수 문자열이여야합니다.");
    }
}