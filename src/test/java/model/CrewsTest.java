package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrewsTest {

    @DisplayName("크루 이름으로 크루를 조회한다")
    @Test
    void findCrewsTest() {
        Crew crew1 = new Crew();
        Crew crew2 = new Crew();
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
        Crew crew1 = new Crew();
        Crew crew2 = new Crew();
        // Given
        Crews crews = new Crews(
                Map.of("호떡", crew1, "밍트", crew2)
        );

        // When & Then
        assertThatThrownBy(() -> crews.findCrewByNickname("사바"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 등록되지 않은 닉네임입니다.");
    }
}
