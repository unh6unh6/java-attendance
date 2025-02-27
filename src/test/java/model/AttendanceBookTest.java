package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttendanceBookTest {

    @DisplayName("크루 닉네임을 통해 출석 기록을 가져온다")
    @Test
    void getCrewAttendanceHistoryTest() {
        AttendanceHistory attendanceHistory = new AttendanceHistory();
        AttendanceBook attendanceBook = new AttendanceBook(Map.of(
                "짱수", attendanceHistory
        ));
        assertThat(attendanceBook.getAttendanceHistoryByNickname("짱수")).isEqualTo(attendanceHistory);
    }
}
