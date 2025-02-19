package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        assertThat(crew.getAttendance()).containsEntry(3, attendanceTime);
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


    @DisplayName("출석을 수정하고 이전의 출석 기록을 반환한다")
    @Test
    void modifyAttendanceTest() {
        // Given
        Crew crew = new Crew("밍트");
        LocalDateTime attendanceTime = LocalDateTime.of(2024, 12, 3, 9, 0);
        crew.doAttendance(attendanceTime);

        LocalDateTime modifyTime = LocalDateTime.of(2024, 12, 3, 9, 50);
        LocalDate todayDate = LocalDate.now();

        // When
        LocalDateTime previousDateTime = crew.doModify(modifyTime, todayDate);

        // Then
        assertAll(
                () -> assertThat(crew.getAttendance()).containsEntry(3, modifyTime),
                () -> assertThat(previousDateTime).isEqualTo(attendanceTime)
        );
    }

    @DisplayName("오늘 또는 미래의 수정 일자일 경우 예외가 발생한다")
    @ParameterizedTest
    @CsvSource({
            "2024-12-03",
            "2024-12-04"
    })
    void invalidModifyDateTest(LocalDate todayDate) {
        // Given
        Crew crew = new Crew("밍트");
        LocalDateTime modifyTime = LocalDateTime.of(2024, 12, 4, 9, 50);

        // When & Then
        assertThatThrownBy(() -> crew.doModify(modifyTime, todayDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 수정 일자는 어제 기록까지만 수정할 수 있습니다.");
    }
}
