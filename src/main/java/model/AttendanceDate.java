package model;

import java.time.LocalDate;

public class AttendanceDate {

    private final LocalDate date;

    public AttendanceDate(final LocalDate date) {
        validateCampusOperatingDay(date);
        this.date = date;
    }

    private void validateCampusOperatingDay(final LocalDate date) {
        if (!CampusOperatingPolicy.isOperatingDate(date)) {
            throw new IllegalArgumentException("[ERROR] 등교일이 아닙니다.");
        }
    }
}
