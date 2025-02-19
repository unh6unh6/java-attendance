package model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum AttendanceType {

    출석(Duration.ofMinutes(0)),
    지각(Duration.ofMinutes(5)),
    결석(Duration.ofMinutes(30));

    public static final LocalTime INITIAL_TIME = LocalTime.MIN;

    AttendanceType(Duration threshold) {
        this.threshold = threshold;
    }

    private final Duration threshold;

    public static AttendanceType from(final LocalDateTime attendanceDateTime) {
        LocalTime startTime = getStartTime(attendanceDateTime.getDayOfWeek());
        LocalTime attendanceTime = LocalTime.from(attendanceDateTime);
        if (attendanceTime.equals(INITIAL_TIME) || attendanceTime.isAfter(startTime.plus(결석.threshold))) {
            return 결석;
        }
        if (attendanceTime.isAfter(startTime.plus(지각.threshold))) {
            return 지각;
        }
        return 출석;
    }

    public static Map<AttendanceType, Integer> countAttendanceType(final List<LocalDateTime> attendanceTimes) {
        Map<AttendanceType, Integer> result = new EnumMap<>(AttendanceType.class);
        result.put(출석, 0);
        result.put(지각, 0);
        result.put(결석, 0);
        for (LocalDateTime attendanceTime : attendanceTimes) {
            result.merge(from(attendanceTime), 1, Integer::sum);
        }
        return result;
    }

    private static LocalTime getStartTime(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.MONDAY) {
            return LocalTime.of(13, 0);
        }
        return LocalTime.of(10, 0);
    }
}
