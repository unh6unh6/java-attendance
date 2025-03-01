package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public enum DismissalType {
    WARNING("경고", 2),
    COUNSEL("면담", 3),
    DISMISSAL("제적", 6),
    NONE("해당없음", 0);

    private static final int LATE_ABSENT_RATIO = 3;

    private final String name;

    private final int threshold;

    DismissalType(final String name, final int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static DismissalType from(final Map<AttendanceType, Integer> result) {
        int convertedAbsentCount = calculateConvertedAbsentCount(result);
        return Arrays.stream(values())
                .filter(value -> convertedAbsentCount >= value.threshold)
                .max(Comparator.comparing(value -> value.threshold))
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }

    private static int calculateConvertedAbsentCount(final Map<AttendanceType, Integer> result) {
        return result.get(AttendanceType.ABSENT) + result.get(AttendanceType.LATE) / LATE_ABSENT_RATIO;
    }
}
