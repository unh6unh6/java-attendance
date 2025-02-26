package model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum AttendanceType {

    PRESENT("출석", Duration.ofMinutes(0)),
    LATE("지각", Duration.ofMinutes(5)),
    ABSENT("결석", Duration.ofMinutes(30));

    public static final LocalTime DEFAULT_TIME = LocalTime.MIN;

    private static final LocalTime MONDAY_START_TIME = LocalTime.of(13, 0);
    private static final LocalTime WEEKDAY_START_TIME = LocalTime.of(10, 0);

    AttendanceType(final String typeName, final Duration threshold) {
        this.typeName = typeName;
        this.threshold = threshold;
    }

    private final String typeName;

    private final Duration threshold;

    public static AttendanceType from(final LocalDateTime attendanceDateTime) {
        LocalTime attendanceTime = LocalTime.from(attendanceDateTime);
        LocalTime startTime = getStartTime(attendanceDateTime.getDayOfWeek());
        return getAttendanceTypeByTime(attendanceTime, startTime);
    }

    private static AttendanceType getAttendanceTypeByTime(final LocalTime attendanceTime, final LocalTime startTime) {
        if (attendanceTime.equals(DEFAULT_TIME) || attendanceTime.isAfter(startTime.plus(ABSENT.threshold))) {
            return ABSENT;
        }
        if (attendanceTime.isAfter(startTime.plus(LATE.threshold))) {
            return LATE;
        }
        return PRESENT;
    }

    public static Map<AttendanceType, Integer> countAttendanceType(final List<LocalDateTime> attendanceTimes) {
        Map<AttendanceType, Integer> result = new EnumMap<>(AttendanceType.class);
        result.put(PRESENT, 0);
        result.put(LATE, 0);
        result.put(ABSENT, 0);
        for (LocalDateTime attendanceTime : attendanceTimes) {
            result.merge(from(attendanceTime), 1, Integer::sum);
        }
        return result;
    }

    private static LocalTime getStartTime(final DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.MONDAY) {
            return MONDAY_START_TIME;
        }
        return WEEKDAY_START_TIME;
    }

    public String getTypeName() {
        return typeName;
    }
}
