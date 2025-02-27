package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDateTime {

    private final LocalDateTime dateTime;

    public AttendanceDateTime(final LocalDateTime dateTime) {
        validateCampusOperatingDay(LocalDate.from(dateTime));
        this.dateTime = dateTime;
    }

    private void validateCampusOperatingDay(final LocalDate date) {
        if (isNotOperatingDay(date)) {
            throw new IllegalArgumentException("[ERROR] 등교일이 아닙니다.");
        }
    }

    private boolean isNotOperatingDay(final LocalDate date) {
        return isWeekend(date.getDayOfWeek()) || Holiday.isHoliday(date);
    }

    private boolean isWeekend(final DayOfWeek dayOfWeek) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }
}
