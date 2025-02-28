package model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;

public enum AttendanceType {
    LATE(Duration.ofMinutes(5)),
    ABSENT(Duration.ofMinutes(30)),
    NONE(Duration.ofMinutes(0));

    private final Duration threshold;

    AttendanceType(final Duration threshold) {
        this.threshold = threshold;
    }

    public static AttendanceType from(
            final AttendanceDate attendanceDate,
            final AttendanceTime attendanceTime
    ) {
        LocalTime educationStartTime = EducationTimePolicy.getStartTime(attendanceDate.getDayOfWeek());
        Duration duration = attendanceTime.getOverDuration(educationStartTime);
        return Arrays.stream(values())
                .filter(value -> !isWithinThreshold(value, duration))
                .max(Comparator.comparing(value -> value.threshold))
                .orElse(NONE);
    }

    private static boolean isWithinThreshold(final AttendanceType value, final Duration duration) {
        return value.threshold.compareTo(duration) >= 0;
    }
}
