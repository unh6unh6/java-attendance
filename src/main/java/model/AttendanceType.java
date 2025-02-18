package model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public enum AttendanceType {
    출석(0),
    지각(5),
    결석(30);

    AttendanceType(int threshold) {
        this.threshold = threshold;
    }

    private final int threshold;

    public static AttendanceType from(final LocalDateTime time) {
        LocalTime startTime = getStartTime(time.getDayOfWeek());
        LocalTime attendanceTime = LocalTime.from(time);
        long durationMinutes = ChronoUnit.MINUTES.between(startTime, attendanceTime);
        if (durationMinutes > 결석.threshold) {
            return 결석;
        }
        if (durationMinutes > 지각.threshold) {
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
