package util;

import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringParserTest {

    @Test
    void parseLocalTime() {
        // Given
        StringParser stringParser = new StringParser();

        // When
        LocalTime parsedLocalTime = stringParser.parseLocalTime("11:11");

        // Then
        Assertions.assertThat(parsedLocalTime).hasHour(11).hasMinute(11);
    }
}