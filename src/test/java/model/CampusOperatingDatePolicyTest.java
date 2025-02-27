package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampusOperatingDatePolicyTest {
    @DisplayName("캠퍼스 운영 날짜인지 확인한다")
    @Test
    void campusOperatingDateTest() {
        assertAll(
                () -> assertThat(CampusOperatingDatePolicy.isOperatingDate(LocalDate.of(2024, 12, 2))).isTrue(),
                () -> assertThat(CampusOperatingDatePolicy.isOperatingDate(LocalDate.of(2024, 12, 3))).isTrue(),
                () -> assertThat(CampusOperatingDatePolicy.isOperatingDate(LocalDate.of(2024, 12, 1))).isFalse(),
                () -> assertThat(CampusOperatingDatePolicy.isOperatingDate(LocalDate.of(2024, 12, 7))).isFalse(),
                () -> assertThat(CampusOperatingDatePolicy.isOperatingDate(LocalDate.of(2024, 12, 25))).isFalse()
        );
    }
}
