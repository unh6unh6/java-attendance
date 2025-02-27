package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDateTime {

    private final LocalDateTime dateTime;

    public AttendanceDateTime(final LocalDateTime dateTime) {
        validateCampusOperatingDay(LocalDate.from(dateTime));
        this.dateTime = dateTime;
    }

    private void validateCampusOperatingDay(final LocalDate date) {
        if (!CampusOperatingPolicy.isOperatingDate(date)) {
            throw new IllegalArgumentException("[ERROR] 등교일이 아닙니다.");
        }
    }
}
