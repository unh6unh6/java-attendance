package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AttendanceDateTimeTest {

    @DisplayName("출석 날짜 및 시간을 생성한다")
    @Test
    void createAttendanceDateTime() {
        LocalDateTime attendanceDateTime = LocalDateTime.of(2024, 12, 2, 9, 50);
        assertThatCode(() -> new AttendanceDateTime(attendanceDateTime))
                .doesNotThrowAnyException();
    }

    @DisplayName("주말이나 공휴일이라면 예외가 발생한다")
    @ParameterizedTest
    @CsvSource({
            "2024-12-01T09:50",
            "2024-12-07T09:50"
    })
    void notOperationDateTest(final LocalDateTime attendanceDateTime) {
        assertThatThrownBy(() -> new AttendanceDateTime(attendanceDateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 등교일이 아닙니다.");

        // TODO : [ERROR] 12월 14일 토요일은 등교일이 아닙니다. 라고 출력되어야 한다.
    }
}
