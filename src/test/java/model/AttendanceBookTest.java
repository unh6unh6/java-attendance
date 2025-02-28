package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttendanceBookTest {

    @DisplayName("크루 닉네임을 통해 출석 기록을 가져온다")
    @Test
    void getCrewAttendanceHistoryTest() {
        AttendanceHistory attendanceHistory = new AttendanceHistory(new HashMap<>());
        AttendanceBook attendanceBook = new AttendanceBook(Map.of(
                "짱수", attendanceHistory
        ));
        assertThat(attendanceBook.getAttendanceHistoryByNickname("짱수")).isEqualTo(attendanceHistory);
    }

    @DisplayName("존재하지 않는 크루라면 예외가 발생한다")
    @Test
    void notExistCrewTest() {
        AttendanceHistory attendanceHistory = new AttendanceHistory(new HashMap<>());
        AttendanceBook attendanceBook = new AttendanceBook(Map.of(
                "짱수", attendanceHistory
        ));
        assertThatThrownBy(() -> attendanceBook.getAttendanceHistoryByNickname("호떡"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 등록되지 않은 닉네임입니다.");
    }
}
