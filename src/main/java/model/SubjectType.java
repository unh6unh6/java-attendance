package model;

import java.util.Arrays;
import java.util.Map;

public enum SubjectType {

    경고(2),
    면담(3),
    제적(6),
    해당없음(0);

    private final int threshold;

    SubjectType(final int threshold) {
        this.threshold = threshold;
    }

    public static SubjectType from(final Map<AttendanceType, Integer> result) {
        int absentCount = result.get(AttendanceType.결석);
        int lateCount = result.get(AttendanceType.지각);
        int convertedAbsentCount = lateCount / 3;
        int totalAbsentCount = absentCount + convertedAbsentCount;
        return Arrays.stream(SubjectType.values())
                .sorted((subjectType1, subjectType2) -> Integer.compare(subjectType2.threshold, subjectType1.threshold))
                .filter(subjectType -> totalAbsentCount >= subjectType.threshold)
                .findFirst()
                .orElse(해당없음);
    }
}
