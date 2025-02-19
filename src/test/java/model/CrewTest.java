package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrewTest {

    @DisplayName("크루 닉네임을 통해 크루를 생성한다")
    @Test
    void createCrewTest() {
        assertThatCode(() -> new Crew("호떡"))
                .doesNotThrowAnyException();
    }

    @DisplayName("출석을 한다")
    @Test
    void checkAttendanceTest() {
        // Given
        Crew crew = new Crew("밍트");
        LocalDateTime attendanceTime = LocalDateTime.of(2024, 12, 3, 9, 0);

        // When
        crew.doAttendance(attendanceTime);

        // Then
        Assertions.assertThat(crew.getAttendance()).containsEntry(3, attendanceTime);
    }

    @DisplayName("이미 출석한 경우 예외가 발생한다")
    @Test
    void alreadyAttendanceTest() {
        // Given
        Crew crew = new Crew("밍트");
        LocalDateTime attendanceTime = LocalDateTime.of(2024, 12, 3, 9, 0);
        crew.doAttendance(attendanceTime);

        // When & Then
        assertThatThrownBy(() -> crew.doAttendance(attendanceTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 이미 출석했습니다. 수정 기능을 이용해주세요.");
    }


    @DisplayName("출석을 수정한다")
    @Test
    void modifyAttendanceTest() {
        // Given
        Crew crew = new Crew("밍트");
        LocalDateTime modifyTime = LocalDateTime.of(2024, 12, 3, 9, 50);

        // When
        crew.doModify(modifyTime);

        // Then
        Assertions.assertThat(crew.getAttendance()).containsEntry(3, modifyTime);
    }
}
