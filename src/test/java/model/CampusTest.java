package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.TimeFormatter;

public class CampusTest {

    // 날짜 O -> 운영 시간
    @DisplayName("주말이나 공휴일이 아니고 운영 시간인지 검증한다")
    @ParameterizedTest
    @CsvSource({
            "2024-12-03T08:00",
            "2024-12-03T23:00"
    })
    void operationTimeTest(final LocalDateTime time) {
        // Given
        Campus campus = new Campus(new TimeFormatter());

        // When & Then
        Assertions.assertThatCode(() -> {
            campus.validateOperationTime(time);
        }).doesNotThrowAnyException();
    }

    // 날짜 X (운영 일자)
    @DisplayName("주말이나 공휴일이면 예외가 발생한다")
    @ParameterizedTest
    @CsvSource({
            "2024-12-01T10:00",
            "2024-12-25T10:00"
    })
    void notOperationDateTest(final LocalDateTime time) {
        // Given
        Campus campus = new Campus(new TimeFormatter());

        // When & Then
        assertThatThrownBy(() -> campus.validateOperationTime(time))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContainingAll("[ERROR]", "등교일이 아닙니다.");
    }

    // 날짜 O -> 운영 시간
}
