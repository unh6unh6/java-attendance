package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeFormatterTest {

    private TimeFormatter timeFormatter;

    @BeforeEach
    void setUp() {
        timeFormatter = new TimeFormatter();
    }

    @DisplayName("LocalDateTime을 출력 형식으로 변환한다")
    @Test
    void localDateTimeFormatTest() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.of(2024, 12, 3, 9, 55);

        // When & Then
        assertThat(timeFormatter.formatDateTime(localDateTime)).isEqualTo("12월 03일 화요일 09:55");
    }

    @DisplayName("LocalDate를 출력 형식으로 변환한다")
    @Test
    void localDateFormatTest() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.of(2024, 12, 3, 9, 55);

        // When & Then
        assertThat(timeFormatter.formatDate(localDateTime)).isEqualTo("12월 03일 화요일");
    }

}