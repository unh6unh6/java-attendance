package model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public enum AttendanceType {
    출석(Duration.ofMinutes(0)),
    지각(Duration.ofMinutes(5)),
    결석(Duration.ofMinutes(30));

    AttendanceType(Duration threshold) {
        this.threshold = threshold;
    }

    private final Duration threshold;

    public static AttendanceType from(final LocalDateTime time) {
        LocalTime startTime = getStartTime(time.getDayOfWeek());
        LocalTime attendanceTime = LocalTime.from(time);
        if (attendanceTime.isAfter(startTime.plus(결석.threshold))) {
            return 결석;
        }
        if (attendanceTime.isAfter(startTime.plus(지각.threshold))) {
            return 지각;
        }
        return 출석;
    }

    private static LocalTime getStartTime(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.MONDAY) {
            return LocalTime.of(13, 0);
        }
        return LocalTime.of(10, 0);
    }
}
