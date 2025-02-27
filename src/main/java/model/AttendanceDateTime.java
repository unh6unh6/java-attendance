package model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class AttendanceDateTime {

    private final LocalDateTime dateTime;

    public AttendanceDateTime(final LocalDateTime dateTime) {
        validateCampusOperatingDay(dateTime);
        this.dateTime = dateTime;
    }

    private void validateCampusOperatingDay(final LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            throw new IllegalArgumentException("[ERROR] 등교일이 아닙니다.");
        }
    }
}
