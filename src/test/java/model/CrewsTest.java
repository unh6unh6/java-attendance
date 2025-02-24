package model;

import static model.AttendanceType.DEFAULT_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dto.DismissalCrewDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrewsTest {

    @DisplayName("크루 이름으로 크루를 조회한다")
    @Test
    void findCrewsTest() {
        Crew crew1 = new Crew(new HashMap<>());
        Crew crew2 = new Crew(new HashMap<>());
        // Given
        Crews crews = new Crews(
                Map.of("호떡", crew1, "밍트", crew2)
        );

        // When & Then
        assertThat(crews.findCrewByNickname("호떡")).isEqualTo(crew1);
    }

    @DisplayName("크루가 존재하지 않는다면 예외를 발생시킨다")
    @Test
    void crewNotExistTest() {
        Crew crew1 = new Crew(new HashMap<>());
        Crew crew2 = new Crew(new HashMap<>());
        // Given
        Crews crews = new Crews(
                Map.of("호떡", crew1, "밍트", crew2)
        );

        // When & Then
        assertThatThrownBy(() -> crews.findCrewByNickname("사바"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 등록되지 않은 닉네임입니다.");
    }

    @DisplayName("제적 위험자를 확인한다")
    @Test
    void findDismissalCrewDtosTest() {
        // Given
        Crew hotteok = new Crew(Map.of(
                2, LocalDateTime.of(LocalDate.of(2024, 12, 2), DEFAULT_TIME),
                3, LocalDateTime.of(LocalDate.of(2024, 12, 3), DEFAULT_TIME),
                4, LocalDateTime.of(2024, 12, 4, 9, 30),
                5, LocalDateTime.of(2024, 12, 5, 9, 30),
                6, LocalDateTime.of(2024, 12, 6, 9, 30),
                9, LocalDateTime.of(2024, 12, 9, 9, 30)
        ));

        Crew mint = new Crew(Map.of(
                2, LocalDateTime.of(LocalDate.of(2024, 12, 2), DEFAULT_TIME),
                3, LocalDateTime.of(LocalDate.of(2024, 12, 3), DEFAULT_TIME),
                4, LocalDateTime.of(LocalDate.of(2024, 12, 4), DEFAULT_TIME),
                5, LocalDateTime.of(2024, 12, 5, 9, 30),
                6, LocalDateTime.of(2024, 12, 6, 9, 30),
                9, LocalDateTime.of(2024, 12, 9, 9, 30)
        ));

        Crew wilson = new Crew(Map.of(
                2, LocalDateTime.of(LocalDate.of(2024, 12, 2), DEFAULT_TIME),
                3, LocalDateTime.of(LocalDate.of(2024, 12, 3), DEFAULT_TIME),
                4, LocalDateTime.of(LocalDate.of(2024, 12, 4), DEFAULT_TIME),
                5, LocalDateTime.of(LocalDate.of(2024, 12, 5), DEFAULT_TIME),
                6, LocalDateTime.of(LocalDate.of(2024, 12, 6), DEFAULT_TIME),
                9, LocalDateTime.of(LocalDate.of(2024, 12, 9), DEFAULT_TIME)
        ));
        Crews crews = new Crews(
                Map.of("호떡", hotteok, "밍트", mint, "윌슨", wilson)
        );
        LocalDate todayDate = LocalDate.of(2024, 12, 10);
        DismissalCrewDto hotteokDto = new DismissalCrewDto("호떡", 2, 0, SubjectType.경고);
        DismissalCrewDto mintDto = new DismissalCrewDto("밍트", 3, 0, SubjectType.면담);
        DismissalCrewDto wilsonDto = new DismissalCrewDto("윌슨", 6, 0, SubjectType.제적);

        // When & Then
        assertThat(crews.findDismissalCrewDtos(todayDate)).containsOnly(hotteokDto, mintDto, wilsonDto);
    }

    // 제적 위험자 조회 결과
    //- 빙티: 결석 3회, 지각 4회 (면담) // 결석 4회, 지각 1회
    //- 이든: 결석 2회, 지각 5회 (면담) // 결석 3회, 지각 2회
    //- 빙봉: 결석 1회, 지각 6회 (면담) // 결석 3회
    //- 쿠키: 결석 2회, 지각 3회 (면담) // 결석 3회
    //- 짱수: 결석 0회, 지각 6회 (경고) // 결석 2회
    @DisplayName("제적 위험자를 정렬한다")
    @Test
    void sortTest() {
        // Given
        DismissalCrewDto bingbong = new DismissalCrewDto("빙봉", 1, 6, SubjectType.면담);
        DismissalCrewDto cookie = new DismissalCrewDto("쿠키", 2, 3, SubjectType.면담);
        DismissalCrewDto bingtee = new DismissalCrewDto("빙티", 3, 4, SubjectType.면담);
        DismissalCrewDto zzangsu = new DismissalCrewDto("짱수", 0, 6, SubjectType.경고);
        DismissalCrewDto eden = new DismissalCrewDto("이든", 2, 5, SubjectType.면담);
        List<DismissalCrewDto> dtos = new ArrayList<>(List.of(bingbong, cookie, bingtee, zzangsu, eden));

        Crews crews = new Crews(new HashMap<>());

        // When
        crews.sortDismissalCrewDtos(dtos);

        // Then
        assertThat(dtos).containsExactly(bingtee, eden, bingbong, cookie, zzangsu);
    }
}
