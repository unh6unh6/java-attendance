package model;

import java.util.Arrays;
import java.util.Map;

public enum SubjectType {

    WARNING("경고", 2),
    COUNSELING("면담", 3),
    EXPULSION("제적", 6),
    NONE("해당없음", 0);

    private static final int CONVERTED_ABSENT_UNIT = 3;

    private final String typeName;

    private final int threshold;

    SubjectType(final String typeName, final int threshold) {
        this.typeName = typeName;
        this.threshold = threshold;
    }

    public static SubjectType from(final Map<AttendanceType, Integer> result) {
        int absentCount = result.get(AttendanceType.ABSENT);
        int lateCount = result.get(AttendanceType.LATE);
        int totalAbsentCount = calculateTotalAbsentCount(lateCount, absentCount);
        return Arrays.stream(SubjectType.values())
                .sorted((subjectType1, subjectType2) -> Integer.compare(subjectType2.threshold, subjectType1.threshold))
                .filter(subjectType -> totalAbsentCount >= subjectType.threshold)
                .findFirst()
                .orElse(NONE);
    }

    public static int calculateTotalLateCount(final int lateCount, final int absentCount) {
        return lateCount + absentCount * CONVERTED_ABSENT_UNIT;
    }

    private static int calculateTotalAbsentCount(final int lateCount, final int absentCount) {
        return absentCount + lateCount / CONVERTED_ABSENT_UNIT;
    }

    public static int compare(final SubjectType type1, final SubjectType type2) {
        return Integer.compare(type2.threshold, type1.threshold);
    }

    public String getTypeName() {
        return typeName;
    }
}
